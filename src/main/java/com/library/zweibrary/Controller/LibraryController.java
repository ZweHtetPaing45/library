package com.library.zweibrary.Controller;


import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.library.zweibrary.LoginData.LoginAdmin;
import com.library.zweibrary.adminRepositories.Repositories;
import com.library.zweibrary.admin_eight.eight.eight;
import com.library.zweibrary.admin_eight.eightRepo.eightRepo;
import com.library.zweibrary.admin_eight.eightService.eightService;
import com.library.zweibrary.admin_five.five.five;
import com.library.zweibrary.admin_five.fiveRepo.fiveRepo;
import com.library.zweibrary.admin_five.fiveService.fiveService;
import com.library.zweibrary.admin_four.four.four;
import com.library.zweibrary.admin_four.fourRepo.fourRepo;
import com.library.zweibrary.admin_four.fourService.fourService;
import com.library.zweibrary.admin_nine.nine.nine;
import com.library.zweibrary.admin_nine.nineRepo.nineRepo;
import com.library.zweibrary.admin_nine.nineService.nineService;
import com.library.zweibrary.admin_seven.seven.seven;
import com.library.zweibrary.admin_seven.sevenRepo.sevenRepo;
import com.library.zweibrary.admin_seven.sevenService.sevenService;
import com.library.zweibrary.admin_six.six.six;
import com.library.zweibrary.admin_six.sixRepo.sixRepo;
import com.library.zweibrary.admin_six.sixService.sixService;
import com.library.zweibrary.admin_ten.ten.ten;
import com.library.zweibrary.admin_ten.tenRepo.tenRepo;
import com.library.zweibrary.admin_ten.tenService.tenService;
import com.library.zweibrary.admin_tho.Tho.Tho;
import com.library.zweibrary.admin_tho.ThoRepo.ThoREpo;
import com.library.zweibrary.admin_tho.ThoService.ThoService;
import com.library.zweibrary.admin_three.three.three;
import com.library.zweibrary.admin_three.threeRepo.threeRepo;
import com.library.zweibrary.admin_three.threeService.threeService;
import com.library.zweibrary.admin_two.two.Two;
import com.library.zweibrary.admin_two.twoRepo.TwoRepo;
import com.library.zweibrary.admin_two.two_Service.TwoService;



@Controller
public class LibraryController {

    @Autowired
    private Repositories repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private  ThoService thoService;

    @Autowired
    private ThoREpo thoRepo;

    @Autowired
    private TwoRepo twoRepo;

    @Autowired
    private TwoService twoService;

    @Autowired
    private threeRepo threerepo;

    @Autowired
    private threeService threeservice;

    @Autowired
    private fourRepo fourrepo;

    @Autowired
    private fourService fourservice;

    @Autowired
    private fiveRepo fiverepo;

    @Autowired
    private fiveService fiveservice;

    @Autowired
    private sixRepo sixrepo;

    @Autowired
    private sixService sixservice;

    @Autowired
    private sevenRepo sevenrepo;

    @Autowired
    private sevenService sevenservice;

    @Autowired
    private eightRepo eightrepo;

    @Autowired
    private eightService eightservice;

    @Autowired
    private nineRepo ninerepo;

    @Autowired
    private nineService nineservice;

    @Autowired
    private tenRepo tenrepo;

    @Autowired
    private tenService tenservice;

    
    @GetMapping()
    public String getadv(Model model) {
        List<Tho> user_tho=thoRepo.findAll();
        List<Two> user_two=twoRepo.findAll();
        List<three> user_three=threerepo.findAll();
        List<four> user_four=fourrepo.findAll();
        List<five> user_five=fiverepo.findAll();
        List<six> user_six=sixrepo.findAll();
        List<seven> user_seven=sevenrepo.findAll();
        List<eight> user_eight=eightrepo.findAll();
        List<nine> user_nine=ninerepo.findAll();
        List<ten> user_ten=tenrepo.findAll();
        model.addAttribute("user_thos",user_tho);
        model.addAttribute("user_twos",user_two);
        model.addAttribute("user_threes",user_three);
        model.addAttribute("user_fours",user_four);
        model.addAttribute("user_fives",user_five);
        model.addAttribute("user_sixs",user_six);
        model.addAttribute("user_sevens",user_seven);
        model.addAttribute("user_eights",user_eight);
        model.addAttribute("user_nines",user_nine);
        model.addAttribute("user_tens",user_ten);

        return "advance-search";
    }

    @GetMapping("/login")
        public String getLogin(){
            return "login";
    }

    @GetMapping("/admin_tho")
    public String getAdmin(Model model){
        List<Tho> thoList=thoRepo.findAll();
        model.addAttribute("thos",thoList);
        return "admin_tho";
    }

    //Pdf file image create
    @GetMapping("/pdf/view/{id}")
    public ResponseEntity<byte[]> viewPdf(@PathVariable Long id) {
        Tho tho=thoRepo.findById(id).orElseThrow();
        byte[] pdfData=tho.getData();
        //This code is image view
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.inline().filename(tho.getName()).build());
        headers.setContentLength(pdfData.length);
        return new ResponseEntity<>(pdfData,headers,HttpStatus.OK);
    }

        //Download File
    @GetMapping("/pdf/download/{id}")
    public ResponseEntity<byte[]> downloadpdf(@PathVariable Long id) {
        Tho tho=thoRepo.findById(id).orElseThrow();
        byte[] pdfData=tho.getData();
        String fileName=tho.getName();
        String encodeFileName=URLEncoder.encode(fileName,StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        //This code is image view
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.add("Content-Disposition","attachment; filename*=UTF-8''"+encodeFileName);
        headers.setContentLength(pdfData.length);
        return new ResponseEntity<>(pdfData,headers,HttpStatus.OK);
    }


    //Delete pdf file admin_tho
    @GetMapping("/pdf/delete/{id}")
    public String getPdfDelete(@PathVariable Long id) {
        thoRepo.deleteById(id);
        return "redirect:/admin_tho";
    }
    
    @PostMapping("/admin_tho")
    public String postTho(@RequestParam("file") MultipartFile file,Model model) throws IOException { 
        thoService.saveTho(file);
        return "redirect:/admin_tho";
    }
    
     //Admin two
    @GetMapping("/admin_two")
    public String getMethodName(Model model) {
        List<Two> two=twoRepo.findAll();
        model.addAttribute("twos",two);
        return "admin_two";
    }
    
    @PostMapping("/admin_two")
    public String postTwo(@RequestParam("file") MultipartFile file,Model model) throws IOException{
        twoService.saveTwo(file);
        return "redirect:/admin_two";
    }

     //Pdf file image create
    @GetMapping("/pdf/two_view/{id}")
    public ResponseEntity<byte[]> two(@PathVariable Long id) {
        Two two=twoRepo.findById(id).orElseThrow();
        byte[] pdfData=two.getData();

        //Myanmar language change
        String fileName=two.getName();
        //FileName UTF-8 encode supposrt browser
        String encodedFileName=URLEncoder.encode(fileName,StandardCharsets.UTF_8).replaceAll("\\+","%20");
        //This code is image view
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        headers.add("Content-Disposition","inline; filename*=UTF-8''"+encodedFileName);
        headers.setContentLength(pdfData.length);
        return new ResponseEntity<>(pdfData,headers,HttpStatus.OK);
    }

        //Download File
        @GetMapping("/pdf/two_download/{id}")
    public ResponseEntity<byte[]> twoDownload(@PathVariable Long id) {
        Two two=twoRepo.findById(id).orElseThrow();
        byte[] pdfData=two.getData();
        String fileName=two.getName();
        String encodeFileName=URLEncoder.encode(fileName,StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        //This code is image view
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.add("Content-Disposition","attachment; filename*=UTF-8''"+encodeFileName);
        headers.setContentLength(pdfData.length);
        return new ResponseEntity<>(pdfData,headers,HttpStatus.OK);
    }


    //Delete pdf file admin_tho
    @GetMapping("/pdf/two_delete/{id}")
    public String gettwo(@PathVariable Long id) {
        twoRepo.deleteById(id);
        return "redirect:/admin_two";
    }


    
     //Admin three
    @GetMapping("/admin_three")
    public String getThree(Model model) {
        List<three> three=threerepo.findAll();
        model.addAttribute("threes",three);
        return "admin_three";
    }
    
    @PostMapping("/admin_three")
    public String postThree(@RequestParam("file") MultipartFile file,Model model) throws IOException{
        threeservice.saveThree(file);
        return "redirect:/admin_three";
    }

     //Pdf file image create
    @GetMapping("/pdf/three_view/{id}")
    public ResponseEntity<byte[]> three(@PathVariable Long id) {
        three three=threerepo.findById(id).orElseThrow();
        byte[] pdfData=three.getData();

        //Myanmar language change
        String fileName=three.getName();
        //FileName UTF-8 encode supposrt browser
        String encodedFileName=URLEncoder.encode(fileName,StandardCharsets.UTF_8).replaceAll("\\+","%20");
        //This code is image view
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        headers.add("Content-Disposition","inline; filename*=UTF-8''"+encodedFileName);
        headers.setContentLength(pdfData.length);
        return new ResponseEntity<>(pdfData,headers,HttpStatus.OK);
    }

        //Download File
    @GetMapping("/pdf/three_download/{id}")
    public ResponseEntity<byte[]> thteeDownload(@PathVariable Long id) {
        three three=threerepo.findById(id).orElseThrow();
        byte[] pdfData=three.getData();
        String fileName=three.getName();
        String encodeFileName=URLEncoder.encode(fileName,StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        //This code is image view
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.add("Content-Disposition","attachment; filename*=UTF-8''"+encodeFileName);
        headers.setContentLength(pdfData.length);
        return new ResponseEntity<>(pdfData,headers,HttpStatus.OK);
    }

    //Delete pdf file admin_tho
    @GetMapping("/pdf/three_delete/{id}")
    public String getthree(@PathVariable Long id) {
        threerepo.deleteById(id);
        return "redirect:/admin_three";
    }


    //admin_four
    @GetMapping("/admin_four")
    public String getFour(Model model) {
        List<four> four=fourrepo.findAll();
        model.addAttribute("fours",four);
        return "admin_four";
    }
    
    @PostMapping("/admin_four")
    public String postFour(@RequestParam("file") MultipartFile file,Model model) throws IOException{
        fourservice.saveFour(file);
        return "redirect:/admin_four";
    }

     //Pdf file image create
    @GetMapping("/pdf/four_view/{id}")
    public ResponseEntity<byte[]> four(@PathVariable Long id) {
        four four=fourrepo.findById(id).orElseThrow();
        byte[] pdfData=four.getData();

        //Myanmar language change
        String fileName=four.getName();
        //FileName UTF-8 encode supposrt browser
        String encodedFileName=URLEncoder.encode(fileName,StandardCharsets.UTF_8).replaceAll("\\+","%20");
        //This code is image view
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        headers.add("Content-Disposition","inline; filename*=UTF-8''"+encodedFileName);
        headers.setContentLength(pdfData.length);
        return new ResponseEntity<>(pdfData,headers,HttpStatus.OK);
    }

        //Download File
    @GetMapping("/pdf/four_download/{id}")
    public ResponseEntity<byte[]> fourDownload(@PathVariable Long id) {
        four four=fourrepo.findById(id).orElseThrow();
        byte[] pdfData=four.getData();
        String fileName=four.getName();
        String encodeFileName=URLEncoder.encode(fileName,StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        //This code is image view
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.add("Content-Disposition","attachment; filename*=UTF-8''"+encodeFileName);
        headers.setContentLength(pdfData.length);
        return new ResponseEntity<>(pdfData,headers,HttpStatus.OK);
    }

    //Delete pdf file admin_tho
    @GetMapping("/pdf/four_delete/{id}")
    public String getfour(@PathVariable Long id) {
        fourrepo.deleteById(id);
        return "redirect:/admin_four";
    }


     //Admin five
    @GetMapping("/admin_five")
    public String getFive(Model model) {
        List<five> five=fiverepo.findAll();
        model.addAttribute("fives",five);
        return "admin_five";
    }
    
    @PostMapping("/admin_five")
    public String postFive(@RequestParam("file") MultipartFile file,Model model) throws IOException{
        fiveservice.saveFive(file);
        return "redirect:/admin_five";
    }

     //Pdf file image create
    @GetMapping("/pdf/five_view/{id}")
    public ResponseEntity<byte[]> five(@PathVariable Long id) {
        five five=fiverepo.findById(id).orElseThrow();
        byte[] pdfData=five.getData();

        //Myanmar language change
        String fileName=five.getName();
        //FileName UTF-8 encode supposrt browser
        String encodedFileName=URLEncoder.encode(fileName,StandardCharsets.UTF_8).replaceAll("\\+","%20");
        //This code is image view
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        headers.add("Content-Disposition","inline; filename*=UTF-8''"+encodedFileName);
        headers.setContentLength(pdfData.length);
        return new ResponseEntity<>(pdfData,headers,HttpStatus.OK);
    }

        //Download File
    @GetMapping("/pdf/five_download/{id}")
    public ResponseEntity<byte[]> fiveDownload(@PathVariable Long id) {
        five five=fiverepo.findById(id).orElseThrow();
        byte[] pdfData=five.getData();
        String fileName=five.getName();
        String encodeFileName=URLEncoder.encode(fileName,StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        //This code is image view
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.add("Content-Disposition","attachment; filename*=UTF-8''"+encodeFileName);
        headers.setContentLength(pdfData.length);
        return new ResponseEntity<>(pdfData,headers,HttpStatus.OK);
    }

    //Delete pdf file admin_tho
    @GetMapping("/pdf/five_delete/{id}")
    public String getfive(@PathVariable Long id) {
        fiverepo.deleteById(id);
        return "redirect:/admin_five";
    }


     //Admin six
    @GetMapping("/admin_six")
    public String gitSix(Model model) {
        List<six> six=sixrepo.findAll();
        model.addAttribute("sixs",six);
        return "admin_six";
    }
    
    @PostMapping("/admin_six")
    public String postSix(@RequestParam("file") MultipartFile file,Model model) throws IOException{
        sixservice.saveSix(file);
        return "redirect:/admin_six";
    }

     //Pdf file image create
    @GetMapping("/pdf/six_view/{id}")
    public ResponseEntity<byte[]> six(@PathVariable Long id) {
        six six=sixrepo.findById(id).orElseThrow();
        byte[] pdfData=six.getData();

        //Myanmar language change
        String fileName=six.getName();
        //FileName UTF-8 encode supposrt browser
        String encodedFileName=URLEncoder.encode(fileName,StandardCharsets.UTF_8).replaceAll("\\+","%20");
        //This code is image view
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        headers.add("Content-Disposition","inline; filename*=UTF-8''"+encodedFileName);
        headers.setContentLength(pdfData.length);
        return new ResponseEntity<>(pdfData,headers,HttpStatus.OK);
    }

        //Download File
    @GetMapping("/pdf/six_download/{id}")
    public ResponseEntity<byte[]> sixDownload(@PathVariable Long id) {
        six six=sixrepo.findById(id).orElseThrow();
        byte[] pdfData=six.getData();
        String fileName=six.getName();
        String encodeFileName=URLEncoder.encode(fileName,StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        //This code is image view
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.add("Content-Disposition","attachment; filename*=UTF-8''"+encodeFileName);
        headers.setContentLength(pdfData.length);
        return new ResponseEntity<>(pdfData,headers,HttpStatus.OK);
    }

    //Delete pdf file admin_tho
    @GetMapping("/pdf/six_delete/{id}")
    public String getsix(@PathVariable Long id) {
        sixrepo.deleteById(id);
        return "redirect:/admin_six";
    }


     //Admin seven
    @GetMapping("/admin_seven")
    public String getSeven(Model model) {
        List<seven> seven=sevenrepo.findAll();
        model.addAttribute("sevens",seven);
        return "admin_seven";
    }
    
    @PostMapping("/admin_seven")
    public String postSeven(@RequestParam("file") MultipartFile file,Model model) throws IOException{
        sevenservice.saveSeven(file);
        return "redirect:/admin_seven";
    }

     //Pdf file image create
    @GetMapping("/pdf/seven_view/{id}")
    public ResponseEntity<byte[]> seven(@PathVariable Long id) {
        seven seven=sevenrepo.findById(id).orElseThrow();
        byte[] pdfData=seven.getData();

        //Myanmar language change
        String fileName=seven.getName();
        //FileName UTF-8 encode supposrt browser
        String encodedFileName=URLEncoder.encode(fileName,StandardCharsets.UTF_8).replaceAll("\\+","%20");
        //This code is image view
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        headers.add("Content-Disposition","inline; filename*=UTF-8''"+encodedFileName);
        headers.setContentLength(pdfData.length);
        return new ResponseEntity<>(pdfData,headers,HttpStatus.OK);
    }

    //Download File
    @GetMapping("/pdf/seven_download/{id}")
    public ResponseEntity<byte[]> sevenDownload(@PathVariable Long id) {
        seven seven=sevenrepo.findById(id).orElseThrow();
        byte[] pdfData=seven.getData();
        String fileName=seven.getName();
        String encodeFileName=URLEncoder.encode(fileName,StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        //This code is image view
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.add("Content-Disposition","attachment; filename*=UTF-8''"+encodeFileName);
        headers.setContentLength(pdfData.length);
        return new ResponseEntity<>(pdfData,headers,HttpStatus.OK);
    }

    //Delete pdf file admin_tho
    @GetMapping("/pdf/seven_delete/{id}")
    public String getseven(@PathVariable Long id) {
        sevenrepo.deleteById(id);
        return "redirect:/admin_seven";
    }

     //Admin eight
    @GetMapping("/admin_eight")
    public String getEight(Model model) {
        List<eight> eight=eightrepo.findAll();
        model.addAttribute("eights",eight);
        return "admin_eight";
    }
    
    @PostMapping("/admin_eight")
    public String postEight(@RequestParam("file") MultipartFile file,Model model) throws IOException{
        eightservice.saveEight(file);
        return "redirect:/admin_eight";
    }

     //Pdf file image create
    @GetMapping("/pdf/eight_view/{id}")
    public ResponseEntity<byte[]> eight(@PathVariable Long id) {
       // eight eight=eightrepo.findById(id).orElseThrow();
        eight eight=eightrepo.findById(id).orElseThrow();
        byte[] pdfData=eight.getData();

        //Myanmar language change
        String fileName=eight.getName();
        //FileName UTF-8 encode supposrt browser
        String encodedFileName=URLEncoder.encode(fileName,StandardCharsets.UTF_8).replaceAll("\\+","%20");
        //This code is image view
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        headers.add("Content-Disposition","inline; filename*=UTF-8''"+encodedFileName);
        headers.setContentLength(pdfData.length);
        return new ResponseEntity<>(pdfData,headers,HttpStatus.OK);
    }

        //Download File
    @GetMapping("/pdf/eight_download/{id}")
    public ResponseEntity<byte[]> eightDownload(@PathVariable Long id) {
        eight eight=eightrepo.findById(id).orElseThrow();
        byte[] pdfData=eight.getData();
        String fileName=eight.getName();
        String encodeFileName=URLEncoder.encode(fileName,StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        //This code is image view
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.add("Content-Disposition","attachment; filename*=UTF-8''"+encodeFileName);
        headers.setContentLength(pdfData.length);
        return new ResponseEntity<>(pdfData,headers,HttpStatus.OK);
    }

    //Delete pdf file admin_tho
    @GetMapping("/pdf/eight_delete/{id}")
    public String geteight(@PathVariable Long id) {
        eightrepo.deleteById(id);
        return "redirect:/admin_eight";
    }

     //Admin nine
    @GetMapping("/admin_nine")
    public String getNine(Model model) {
        List<nine> nine=ninerepo.findAll();
        model.addAttribute("nines",nine);
        return "admin_nine";
    }
    
    @PostMapping("/admin_nine")
    public String postNine(@RequestParam("file") MultipartFile file,Model model) throws IOException{
        nineservice.saveNine(file);
        return "redirect:/admin_nine";
    }

     //Pdf file image create
    @GetMapping("/pdf/nine_view/{id}")
    public ResponseEntity<byte[]> nine(@PathVariable Long id) {
        nine nine=ninerepo.findById(id).orElseThrow();
        byte[] pdfData=nine.getData();

        //Myanmar language change
        String fileName=nine.getName();
        //FileName UTF-8 encode supposrt browser
        String encodedFileName=URLEncoder.encode(fileName,StandardCharsets.UTF_8).replaceAll("\\+","%20");
        //This code is image view
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        headers.add("Content-Disposition","inline; filename*=UTF-8''"+encodedFileName);
        headers.setContentLength(pdfData.length);
        return new ResponseEntity<>(pdfData,headers,HttpStatus.OK);
    }

        //Download File
    @GetMapping("/pdf/nine_download/{id}")
    public ResponseEntity<byte[]> nineDownload(@PathVariable Long id) {
        nine nine=ninerepo.findById(id).orElseThrow();
        byte[] pdfData=nine.getData();
        String fileName=nine.getName();
        String encodeFileName=URLEncoder.encode(fileName,StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        //This code is image view
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.add("Content-Disposition","attachment; filename*=UTF-8''"+encodeFileName);
        headers.setContentLength(pdfData.length);
        return new ResponseEntity<>(pdfData,headers,HttpStatus.OK);
    }

    //Delete pdf file admin_tho
    @GetMapping("/pdf/nine_delete/{id}")
    public String getnine(@PathVariable Long id) {
        ninerepo.deleteById(id);
        return "redirect:/admin_nine";
    }


     //Admin ten
    @GetMapping("/admin_ten")
    public String getTen(Model model) {
        List<ten> ten=tenrepo.findAll();
        model.addAttribute("tens",ten);
        return "admin_ten";
    }
    
    @PostMapping("/admin_ten")
    public String postTen(@RequestParam("file") MultipartFile file,Model model) throws IOException{
        tenservice.saveTen(file);
        return "redirect:/admin_ten";
    }

     //Pdf file image create
    @GetMapping("/pdf/ten_view/{id}")
    public ResponseEntity<byte[]> ten(@PathVariable Long id) {
        ten ten=tenrepo.findById(id).orElseThrow();
        byte[] pdfData=ten.getData();

        //Myanmar language change
        String fileName=ten.getName();
        //FileName UTF-8 encode supposrt browser
        String encodedFileName=URLEncoder.encode(fileName,StandardCharsets.UTF_8).replaceAll("\\+","%20");
        //This code is image view
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        headers.add("Content-Disposition","inline; filename*=UTF-8''"+encodedFileName);
        headers.setContentLength(pdfData.length);
        return new ResponseEntity<>(pdfData,headers,HttpStatus.OK);
    }

    //Download File
    @GetMapping("/pdf/ten_download/{id}")
    public ResponseEntity<byte[]> tenDownload(@PathVariable Long id) {
        ten ten=tenrepo.findById(id).orElseThrow();
        byte[] pdfData=ten.getData();
        String fileName=ten.getName();
        String encodeFileName=URLEncoder.encode(fileName,StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        //This code is image view
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.add("Content-Disposition","attachment; filename*=UTF-8''"+encodeFileName);
        headers.setContentLength(pdfData.length);
        return new ResponseEntity<>(pdfData,headers,HttpStatus.OK);
    }

    //Delete pdf file admin_tho
    @GetMapping("/pdf/ten_delete/{id}")
    public String getten(@PathVariable Long id) {
        tenrepo.deleteById(id);
        return "redirect:/admin_ten";
    }


    @GetMapping("/registry")
    public String getMethodName() {
        return "registry";
    }

    @PostMapping("/registry")
    public String postMethodName(@RequestParam String username,@RequestParam String password) {
        LoginAdmin login=new LoginAdmin();
        login.setUsername(username);
        login.setPassword(passwordEncoder.encode(password));
        repo.save(login);
        return "login";
    }
    
    @GetMapping("/tho")
    public String GetTho(Model model) {
        List<Tho> user_tho=thoRepo.findAll();
        model.addAttribute("user_thos",user_tho);
        return "tho";
    }

    
    @GetMapping("/two")
    public String getTwo(Model model) {
        List<Two> user_two=twoRepo.findAll();
        model.addAttribute("user_twos",user_two);
        return "two";
    }
    
    @GetMapping("/three")
    public String getthree(Model model) {
        List<three> user_three=threerepo.findAll();
        model.addAttribute("user_threes",user_three);
        return "three";
    }

        @GetMapping("/four")
    public String getfour(Model model) {
        List<four> user_four=fourrepo.findAll();
        model.addAttribute("user_fours",user_four);
        return "four";
    }

        @GetMapping("/five")
    public String getfive(Model model) {
        List<five> user_five=fiverepo.findAll();
        model.addAttribute("user_fives",user_five);
        return "five";
    }

        @GetMapping("/six")
    public String getsix(Model model) {
        List<six> user_six=sixrepo.findAll();
        model.addAttribute("user_sixs",user_six);
        return "six";
    }

        @GetMapping("/seven")
    public String getseven(Model model) {
        List<seven> user_seven=sevenrepo.findAll();
        model.addAttribute("user_sevens",user_seven);
        return "seven";
    }

        @GetMapping("/eight")
    public String geteight(Model model) {
        List<eight> user_eight=eightrepo.findAll();
        model.addAttribute("user_eights",user_eight);
        return "eight";
    }

        @GetMapping("/nine")
    public String getnine(Model model) {
        List<nine> user_nine=ninerepo.findAll();
        model.addAttribute("user_nines",user_nine);
        return "nine";
    }

        @GetMapping("/ten")
    public String getten(Model model) {
        List<ten> user_ten=tenrepo.findAll();
        model.addAttribute("user_tens",user_ten);
        return "ten";
    }


}
