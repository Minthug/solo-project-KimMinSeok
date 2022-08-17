package com.section4.Section4SoloProject.member.repository;

import com.section4.Section4SoloProject.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO MEMBER (NAME,PASSWORD,COMPANY_NAME,COMPANY_TYPE,COMPANY_LOCATION) VALUES ('김민석','1234','CODECODE',005,001) ;", nativeQuery = true)
    public void addDummy();

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO MEMBER (NAME,PASSWORD,COMPANY_NAME,COMPANY_TYPE,COMPANY_LOCATION) VALUES ('김코딩','1234','CODECODE',005,001) ;", nativeQuery = true)
    public void addDummy2();

    default List<Member> test(EntityManager em, String query, int size, int page) {
        return em.createNativeQuery(query, Member.class).setFirstResult(page*size).setMaxResults(size).getResultList();
    }
}
