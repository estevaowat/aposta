package com.ewcode.friendsbigball.domain.common.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
public class BetPrimaryKey implements Serializable {
    @Serial
    private static final long serialVersionUID = -2079323118226447683L;
    @ManyToOne
    @JoinColumn(name = "costumer_id", nullable = false)
    private Costumer costumer;
    public Costumer getCostumer() {
        return costumer;
    }
    public void setCostumer(Costumer costumer) {
        this.costumer = costumer;
    }
}
