package controller;

import model.TaxRule;

public class TaxController {
    private TaxRule taxRule;

    public TaxController(TaxRule taxRule) {
        this.taxRule = taxRule;
    }

    public TaxRule getTaxRule() {
        return taxRule;
    }

    public void updateTaxRule(double pf, double tax, double bonus, double overtime) {
        taxRule.setDefaultPfPercent(pf);
        taxRule.setDefaultTaxPercent(tax);
        taxRule.setDefaultBonus(bonus);
        taxRule.setOvertimeRatePerHour(overtime);
    }
}