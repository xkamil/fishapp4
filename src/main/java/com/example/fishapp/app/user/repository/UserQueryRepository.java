package com.example.fishapp.app.user.repository;

import java.util.Optional;
import java.util.UUID;
import com.example.fishapp.app.user.model.QUser;
import com.example.fishapp.app.user.view.QUserView;
import com.example.fishapp.app.user.view.UserView;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserQueryRepository {

    private final JPAQueryFactory queryFactory;

    public UserQueryRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public Optional<UserView> findByUserId(UUID userId) {
        QUser user = QUser.user;
        UserView userView = queryFactory.select(new QUserView(user.id, user.firstName))
                .from(user)
                .where(user.id.eq(userId))
                .fetchOne();

        return Optional.ofNullable(userView);
    }


}