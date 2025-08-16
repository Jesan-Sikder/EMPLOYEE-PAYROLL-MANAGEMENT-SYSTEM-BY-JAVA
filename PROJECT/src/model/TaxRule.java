package model;


public class TaxRule {
    private double defaultPfPercent;
    private double defaultTaxPercent;
    private double defaultBonus;
    private double overtimeRatePerHour;

    public TaxRule(double defaultPfPercent, double defaultTaxPercent, double defaultBonus, double overtimeRatePerHour) {
        this.defaultPfPercent = defaultPfPercent;
        this.defaultTaxPercent = defaultTaxPercent;
        this.defaultBonus = defaultBonus;
        this.overtimeRatePerHour = overtimeRatePerHour;
    }

    public double getDefaultPfPercent() { return defaultPfPercent; }
    public void setDefaultPfPercent(double defaultPfPercent) { this.defaultPfPercent = defaultPfPercent; }

    public double getDefaultTaxPercent() { return defaultTaxPercent; }
    public void setDefaultTaxPercent(double defaultTaxPercent) { this.defaultTaxPercent = defaultTaxPercent; }

    public double getDefaultBonus() { return defaultBonus; }
    public void setDefaultBonus(double defaultBonus) { this.defaultBonus = defaultBonus; }

    public double getOvertimeRatePerHour() { return overtimeRatePerHour; }
    public void setOvertimeRatePerHour(double overtimeRatePerHour) { this.overtimeRatePerHour = overtimeRatePerHour; }
}