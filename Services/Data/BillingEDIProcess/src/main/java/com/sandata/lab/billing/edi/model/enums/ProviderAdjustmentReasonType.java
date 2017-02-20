package com.sandata.lab.billing.edi.model.enums;


public enum ProviderAdjustmentReasonType
{
    Unknown,
    AccerlerationOfBenefits,
    Adjustment,
    AppliedToBorrowersAccount,
    AuthorizedReturn,
    BadDebtAdjustment,
    Bonus,
    CapitalPassthru,
    CapitationInterest,
    CapitationPayment,
    CertifiedRegisteredNurseAnesthetistPassthru,
    DirectMedicalEducationPassthru,
    EarlyPaymentAllowance,
    ForwardingBalance,
    FundAllocation,
    GraduateMedicalEducationPassthru,
    HemopheliaClottingFactorSupplement,
    IncentivePremiumPayment,
    InterestOwed,
    InterestPenaltyCharge,
    InterimSettlement,
    InternalRevenueServiceWithholding,
    LateCharge,
    Levy,
    LumpSum,
    NonReimbursable,
    OffsetForAffiliatedProviders,
    OrganAcquisitionPassthru,
    OriginationFee,
    OverpaymentRecovery,
    PaymentFinal,
    Penalty,
    PeriodicInterimPayment,
    Rebate,
    RecoveryAllowance,
    RetroActivityAdjustment,
    ReturnOnEquity,
    StudentLoanRepayment,
    TemporaryAllowance,
    ThirdPartyLiability,
    UnspecifiedRecovery,
    Withholding;

    public int getValue()
    {
        return this.ordinal();
    }

    public static ProviderAdjustmentReasonType forValue(int value)
    {
        return values()[value];
    }
}