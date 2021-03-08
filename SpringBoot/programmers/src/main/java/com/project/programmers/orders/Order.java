package com.project.programmers.orders;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static java.time.LocalDateTime.now;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static com.google.common.base.Preconditions.checkNotNull;

public class Order {
    private final Long seq;
    private Long userSeq;
    private Long productSeq;
    private Long reviewSeq;

    private String state;

    private String requestMsg;
    private String rejectMsg;

    private LocalDateTime completedAt;
    private LocalDateTime rejectedAt;
    private LocalDateTime createAt;

    public Order(Long userSeq, Long productSeq, String state, String requestMsg) {
        this(null, userSeq, productSeq, null, state, requestMsg, null, null, null, null);
    }

    public Order(Long seq, Long userSeq, Long productSeq, Long reviewSeq, String state, String requestMsg, String rejectMsg, LocalDateTime completedAt, LocalDateTime rejectedAt, LocalDateTime createAt) {
        checkNotNull(userSeq, "userSeq must be provided");
        checkNotNull(productSeq, "productSeq must be provided");
        checkArgument(isNotEmpty(state), "state must be provided");
        checkArgument(
                isEmpty(requestMsg) || requestMsg.length() <= 1000,
                "requestMsg length must be less than 1000 characters"
        );

        this.seq = seq;
        this.userSeq = userSeq;
        this.productSeq = productSeq;
        this.reviewSeq = reviewSeq;
        this.state = state;
        this.requestMsg = requestMsg;
        this.rejectMsg = rejectMsg;
        this.completedAt = completedAt;
        this.rejectedAt = rejectedAt;
        this.createAt = defaultIfNull(createAt, now());
    }

    public Long getSeq() {
        return seq;
    }

    public Long getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(Long userSeq) {
        checkNotNull(userSeq, "userSeq must be provided");
        this.userSeq = userSeq;
    }

    public Long getProductSeq() {
        return productSeq;
    }

    public void setProductSeq(Long productSeq) {
        checkNotNull(productSeq, "productSeq must be provided");
        this.productSeq = productSeq;
    }

    public Long getReviewSeq() {
        return reviewSeq;
    }

    public void setReviewSeq(Long reviewSeq) {
        this.reviewSeq = reviewSeq;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        checkArgument(isNotEmpty(state), "state must be provided");
        this.state = state;
    }

    public String getRequestMsg() {
        return requestMsg;
    }

    public void setRequestMsg(String requestMsg) {
        checkArgument(
                isEmpty(requestMsg) || requestMsg.length() <= 1000,
                "requestMsg length must be less than 1000 characters"
        );
        this.requestMsg = requestMsg;
    }

    public String getRejectMsg() {
        return rejectMsg;
    }

    public void setRejectMsg(String rejectMsg) {
        this.rejectMsg = rejectMsg;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public LocalDateTime getRejectedAt() {
        return rejectedAt;
    }

    public void setRejectedAt(LocalDateTime rejectedAt) {
        this.rejectedAt = rejectedAt;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(seq, order.seq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seq);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("seq", seq)
                .append("userSeq", userSeq)
                .append("productSeq", productSeq)
                .append("reviewSeq", reviewSeq)
                .append("state", state)
                .append("requestMsg", requestMsg)
                .append("rejectMsg", rejectMsg)
                .append("completedAt", completedAt)
                .append("rejectedAt", rejectedAt)
                .append("createAt", createAt)
                .toString();
    }

    static public class Builder {
        private Long seq;
        private Long userSeq;
        private Long productSeq;
        private Long reviewSeq;
        private String state;
        private String requestMsg;
        private String rejectMsg;
        private LocalDateTime completedAt;
        private LocalDateTime rejectedAt;
        private LocalDateTime createAt;

        public Builder() {/*empty*/}

        public Builder(Order order) {
            this.seq = order.seq;
            this.userSeq = order.userSeq;
            this.productSeq = order.productSeq;
            this.reviewSeq = order.reviewSeq;
            this.state = order.state;
            this.requestMsg = order.requestMsg;
            this.rejectMsg = order.rejectMsg;
            this.completedAt = order.completedAt;
            this.rejectedAt = order.rejectedAt;
            this.createAt = order.createAt;
        }

        public Builder seq(Long seq) {
            this.seq = seq;
            return this;
        }

        public Builder userSeq(Long userSeq) {
            this.userSeq = userSeq;
            return this;
        }

        public Builder productSeq(Long productSeq) {
            this.productSeq = productSeq;
            return this;
        }

        public Builder reviewSeq(Long reviewSeq) {
            this.reviewSeq = reviewSeq;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder requestMsg(String requestMsg) {
            this.requestMsg = requestMsg;
            return this;
        }

        public Builder rejectMsg(String rejectMsg) {
            this.rejectMsg = rejectMsg;
            return this;
        }

        public Builder completedAt(LocalDateTime completedAt) {
            this.completedAt = completedAt;
            return this;
        }

        public Builder rejectedAt(LocalDateTime rejectedAt) {
            this.rejectedAt = rejectedAt;
            return this;
        }

        public Builder createAt(LocalDateTime createAt) {
            this.createAt = createAt;
            return this;
        }

        public Order build() {
            return new Order(
                    seq,
                    userSeq,
                    productSeq,
                    reviewSeq,
                    state,
                    requestMsg,
                    rejectMsg,
                    completedAt,
                    rejectedAt,
                    createAt
            );
        }
    }
}