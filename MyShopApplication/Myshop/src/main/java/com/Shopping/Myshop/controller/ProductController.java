package com.Shopping.Myshop.controller;


import com.Shopping.Myshop.dto.AddProductRequest;
import com.Shopping.Myshop.dto.MyResponse;
import com.Shopping.Myshop.email.EmailWithAttachment;
import com.Shopping.Myshop.model.ProductDetails;
import com.Shopping.Myshop.repository.ProductRepository;
import com.Shopping.Myshop.service.ProductService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@AllArgsConstructor
public class ProductController {

    ProductService productService;
    ProductRepository productRepository;

    private JavaMailSender mailSender;


    private SimpleMailMessage preConfiguredMessage;

    private EmailWithAttachment emailWithAttachment;


    @PostMapping(value = "/product/add")
    public MyResponse addProduct(@RequestBody AddProductRequest request) {
        productService.addProduct(request);
        MyResponse m = MyResponse.getMyResponseObject();
        m.setMessage("Product Added Successfully");
        m.setStatus("Passed");
        m.setResponse(request);
        return m;
    }

    @DeleteMapping(value = "product/delete/{id}")
    public void deleteProduct(@PathVariable("id") long product_id) {
        System.out.println(product_id);
        productService.deleteProduct(product_id);
        System.out.println("Product ID is Deleted");
    }

    @GetMapping(value = "/products")
    public MyResponse getProducts() {
        MyResponse m = MyResponse.getMyResponseObject();
        m.setMessage("Product Displayed Successfully");
        m.setStatus("Passed");
        m.setResponse(productService.getProducts());
        return m;
    }


    @PostMapping("/email")
    public void sendMail(@RequestParam("to") String to, @RequestParam("subject") String subject,
                         @RequestParam("body") String body) {

        mailSender.send(preConfiguredMessage);
    }

    @PostMapping("/emailWithAttach")
    public String emailWithAttach(@RequestParam("to") String to) {
        emailWithAttachment.sendMailWithAttachment();
        return "Mail Sent";
    }

    @PostMapping(value = "/product/upload")
    public void uploadProduct(@RequestParam("file") MultipartFile file, @RequestParam("productName") String productName,
                              @RequestParam("productDesc") String productDesc, @RequestParam("price") long price,
                              @RequestParam("userId") long userId, @RequestParam("productId") long productId) {

        productService.uploadProduct(file, productName, productDesc, price, userId, productId);

    }

    //Refer URL:https://medium.com/@sampathsl/changing-the-existing-text-in-a-pdf-d82a16219c5c
    @GetMapping(value = "/product/replacePDFdata")
    void replacePDFdata() throws IOException {
        String DEST = "E:\\Workspace\\fileupload\\Gautam_stream.pdf";
        String SRC = "E:\\Workspace\\fileupload\\Gautamimage.pdf";

        //Reads and parses a PDF document
        PdfReader pdfReader = new PdfReader(SRC);
        //A dictionary is an associative table containing pairs of objects.
        // The first element of each pair is called the key and the second element is called the value.
        PdfDictionary dictionary = pdfReader.getPageN(1);
        PdfObject object = dictionary.getDirectObject(PdfName.CONTENTS);
        if (object instanceof PRStream) {
            //Created PDF Stream
            PRStream prStream = (PRStream) object;
            byte[] data = PdfReader.getStreamBytes(prStream);
            String str = new String(data);
            str = str.replace("Gautam", "Pravin");
            prStream.setData(str.getBytes());
        }
        try {

            //PdfStamper to write the altered file to a FileOutputStream
            PdfStamper stamper = new PdfStamper(pdfReader, new FileOutputStream(DEST));
            stamper.close();
            pdfReader.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

    //Refer URL : https://www.baeldung.com/java-pdf-creation
    @GetMapping(value = "/product/generatePDF")
    public void generatePDF() {

        Document document = new Document();

        try {
            File f = new File("E:\\Workspace\\fileupload\\Gautam.jpg");

            //every element added to this document will be written to the file specified.
            PdfWriter.getInstance(document, new FileOutputStream("E:\\Workspace\\fileupload\\Gautamimage.pdf"));
            document.open();
            Image img = Image.getInstance(f.getAbsolutePath());
            //Declare Font Style and Color
            Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, BaseColor.RED);

            //This is the smallest significant part of text that can be added to a document
            Chunk chunk = new Chunk("Gautam Chauhan", font);
            document.add(img);
            document.add(chunk);
            document.close();

        } catch (Exception e) {
            throw new RuntimeException("Error while creating PDF file: " + e);
        }
    }

    //Refer URL: https://stackoverflow.com/questions/35680932/download-a-file-from-spring-boot-rest-service
    @GetMapping("/product/download/{id}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable("id") long product_id) throws IOException {

        final String SERVER_LOCATION = "E:\\Workspace\\fileupload\\";
        HttpHeaders header = new HttpHeaders();
        //to process the response payload and additional information such as filename when user saves it locally
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=img.jpg");
        //Not Cache
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        ProductDetails f = productRepository.findById(product_id).get();
        File file = new File(SERVER_LOCATION + f.getFileName());
        Path path = Paths.get(SERVER_LOCATION + f.getFileName());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        //To send different types of file we need using Response Entity (as we used to send only JSON data)
        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);

    }

}
