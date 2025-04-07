package kr.ac.hansung.cse.controller;

import jakarta.validation.Valid;
import kr.ac.hansung.cse.model.Offer;
import kr.ac.hansung.cse.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OfferController {

    @Autowired
    private OfferService offerService;

    @GetMapping("/offers")
    public String showOffers(Model model) {  //view 이름을 반환해야 하므로 모든 컨트롤러 반환형은 String
        List<Offer> offers = offerService.getAllOffers();
        model.addAttribute("key_offers", offers);
        //                                key         value
        return "offers"; //view 이름
    }
    
    //createoffer에 맞는 View를 전달하기위한 컨트롤러
    @GetMapping("/createoffer")
    public String createOffer(Model model) {
        model.addAttribute("offer", new Offer());
        
        return "createoffer"; 
    }


    //사용자의
    @PostMapping("docreate")
    public String doCreate(Model model, @Valid Offer offer, BindingResult result){
        if(result.hasErrors()) { //reqeustBody의 유효성검사에서 걸린경우
            System.out.println("==== From data does not vlidated ====");
            
            List<ObjectError> errors = result.getAllErrors();  //ObjectError 클래스 자료형의 리스트


            //에러 내용들을 돌면서 모두 출력
            for(ObjectError error : errors) {
                System.out.println(error.getDefaultMessage());
            }
            return "createoffer";  //에러난 경우에도 view 이름 전달해야함
        }
        
        offerService.insertOffer(offer);
        return "offercreated";             //이 컨트롤러에서 진행된 작업이 끝난 뒤에 보여질 View 이름
    }
    
}
