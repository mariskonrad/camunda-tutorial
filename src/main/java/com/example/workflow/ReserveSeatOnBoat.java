package com.example.workflow;

import jakarta.inject.Named;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.math.BigDecimal;

@Named // para conseguir chamar pelo nome da classe em vez do pacote
public class ReserveSeatOnBoat implements JavaDelegate {

    private static final BigDecimal FIRST_CLASS_PRICE = new BigDecimal("10000");
    private static final BigDecimal BUSINESS_CLASS_PRICE = new BigDecimal("5000");

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String money = "0.0";
        String ticketType = "Coach";

        money = (String) delegateExecution.getVariable("money");
        BigDecimal moneyBigDecimal = new BigDecimal(money);

        if (moneyBigDecimal.compareTo(FIRST_CLASS_PRICE) >= 0) {
            ticketType = "First Class";
        } else if (moneyBigDecimal.compareTo(BUSINESS_CLASS_PRICE) >= 0 ) {
            ticketType = "Business Class";
        }

        delegateExecution.setVariable("ticketType", ticketType);
    }
}
