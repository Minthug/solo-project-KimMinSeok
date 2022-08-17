package com.section4.Section4SoloProject.member.Controller;

import com.section4.Section4SoloProject.member.Dto.MemberResponse;
import com.section4.Section4SoloProject.member.entity.Member;
import com.section4.Section4SoloProject.member.mapper.MemberMapper;
import com.section4.Section4SoloProject.member.service.MemberService;
import com.sun.istack.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class MemberController {
    private MemberService memberService;
    private MemberMapper memberMapper;
    public MemberController(MemberService memberService, MemberMapper memberMapper) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
    }

    @GetMapping("/")
    public ResponseEntity allMember (@RequestParam(required = false, value = "page", defaultValue = "0") Integer page,
                                     @RequestParam(required = false, value = "size", defaultValue = "20") Integer size) {
        Page memberList = memberService.getMemberList(page, size);

        return new ResponseEntity<>(new MemberResponse(memberList.getContent(), (Pageable) memberList.getPageable()),HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity allMemberTest(@RequestParam(required = false, value = "page", defaultValue = "0") Integer page,
                                        @RequestParam(required = false, value = "size", defaultValue = "20")Integer size) {

        return new ResponseEntity<>(memberService.test(page,size), HttpStatus.OK);
    }

    @GetMapping("/init")
    public ResponseEntity init(@RequestParam(required = false, value = "page", defaultValue = "0") Integer page,
                               @RequestParam(required = false, value = "size", defaultValue = "20") Integer size){
        memberService.init();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity findMember (@RequestParam(required = false, value = "id", defaultValue = "***") @NotNull String id,
                                      @RequestParam(required = false, value = "name", defaultValue = "***" ) String name,
                                      @RequestParam(required = false, value = "password", defaultValue = "***") String password,
                                      @RequestParam(required = false, value = "company_name", defaultValue = "***") String company_name,
                                      @RequestParam(required = false, value = "company_type", defaultValue = "***") String company_type,
                                      @RequestParam(required = false, value = "company_location", defaultValue = "***") String company_location,
                                      @RequestParam(required = false, value = "page", defaultValue = "0") Integer page,
                                      @RequestParam(required = false, value = "size", defaultValue = "20") Integer size) {

        String query = "SELECT * FROM MEMBER m";
        boolean addWhere = false;

        if(!id.equals("***")){
            if(query.indexOf("where") == -1) {
                query += "where";
            }
            query += "m.id = "+id+" ";
        }

        if(!name.equals("***")){
            if(query.indexOf("where") == -1) {
                query += " where ";
            }else {
                query += " and ";
            }
            query += " m.name = \'"+name+"\' ";
        }

        if(!company_name.equals("***")) {
            if(query.indexOf("where") == -1 ){
                query += " where ";
            } else {
                query += " and ";
            }
            query += " m.company_name = "+company_name+" ";
        }

        if(!company_type.equals("***")) {
         if(query.indexOf("where") == -1) {
             query += " where ";
         } else {
             query += " and ";
         }
         query += " m.company_location = "+company_location+" ";
        }

        if(!company_location.equals("***")) {
            if(query.indexOf("where") == -1) {
                query += " where ";
            } else {
                query += " and ";
            }
            query += " m.company_location = "+company_location+" ";
        }

        if(query.equals("Select * From Member")) {
            System.out.println("Nothing checked");
            query = "Select * From Member";
        }

        System.out.println("============\n\n"+query);
        List<Member> memberList = memberService.findMember(query, size, page);

        return new ResponseEntity<>(memberList, HttpStatus.OK);
    }
}
