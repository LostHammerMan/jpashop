package com.jpabook.jpashop.repository;

import com.jpabook.jpashop.domain.Order;
import com.jpabook.jpashop.domain.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    public Order findOne(Long id){
        return em.find(Order.class, id);
    }

    // 검색 기능(동적 쿼리 필요)
    public List<Order> findAllByString(OrderSearch orderSearch){

        // JPQL 방법
        String jpql = "select o from Order o join o.member m";
        boolean isFirstCondition = true;

        // 주문 상태 검색(동적 쿼리)
        if (orderSearch.getOrderStatus() != null){
            if (isFirstCondition){
                jpql += " where";
                isFirstCondition = false;
            }else {
                jpql += " and";
            }

            jpql += " o.status = :status";
        }

        // 회원 이름 검색
        if (StringUtils.hasText(orderSearch.getMemberName())){
            if (isFirstCondition){
                jpql += " where";
                isFirstCondition = false;
            }else {
                jpql += "and";
            }
            jpql += " m.name like :name";
        }

        TypedQuery<Order> query = em.createQuery(jpql, Order.class)
                .setMaxResults(1000);

        if (orderSearch.getOrderStatus() != null){
            query = query.setParameter("status", orderSearch.getOrderStatus());
        }
        if (StringUtils.hasText(orderSearch.getMemberName())){
            query = query.setParameter("name", orderSearch.getMemberName());
        }

        return query.getResultList();

       /* List<Order> resultList =  em.createQuery(jpql, Order.class)
//                .setFirstResult(100) // start position, 페이징 하는 경우 사용
                .setMaxResults(1000) // 최대 조회수
                .getResultList();*/

       /* List<Order> resultList =  em.createQuery("select o from Order o join o.member m" +
                        " where o.status = :status " +
                        "and m.name like :name ", Order.class)
                .setParameter("status", orderSearch.getOrderStatus())
                .setParameter("name", orderSearch.getMemberName())
//                .setFirstResult(100) // start position, 페이징 하는 경우 사용
                .setMaxResults(1000) // 최대 조회수
                .getResultList();*/

//        return resultList;

    }

    // 2번째 방법 : JPA Criteria(jpa 가 표준으로 제공) - 참고만, 단점 : 유지보수 어려움
    public List<Order> findByCriteria(OrderSearch orderSearch){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> o = cq.from(Order.class);
        Join<Object, Object> m = o.join("member", JoinType.INNER);

        o.join("member", JoinType.INNER);

        List<Predicate> criteria = new ArrayList<>();

        // 주문 상태 검색
        if (orderSearch.getOrderStatus() != null){
            Predicate status = cb.equal(o.get("status"), orderSearch.getOrderStatus());
            criteria.add(status);
        }

        // 회원 이름 검색
        if (StringUtils.hasText(orderSearch.getMemberName())){
            Predicate name =
                    cb.like(m.<String>get("name"), "%" + orderSearch.getMemberName() + " %");
            criteria.add(name);
        }

        cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
        TypedQuery<Order> query = em.createQuery(cq).setMaxResults(1000);
        return query.getResultList();
    }

    // 3번째 방법 : queryDSL (권장 방법)



}
