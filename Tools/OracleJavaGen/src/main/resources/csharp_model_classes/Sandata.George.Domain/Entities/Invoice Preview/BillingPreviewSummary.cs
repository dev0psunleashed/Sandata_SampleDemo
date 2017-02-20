using System;
using System.Collections.Generic;
using System.Runtime.Serialization;
using Sandata.George.Common.Helpers;

namespace Sandata.George.Domain.Entities
{
    [DataContract]
    public class BillingPreviewSummary
    {
        [DataMember]
        public String TransactionID { get; set; }

        [IgnoreDataMember]
        public DateTime SubmissionDate { get; set; }

        [DataMember(Name = "SubmissionDate", IsRequired = true)]
        public string SubmissionDateStr
        {
            get
            {
                return DateTimeHelper.ToDateTimeString(SubmissionDate);
            }
            set
            {
                if (!String.IsNullOrEmpty(value))
                {
                    SubmissionDate = DateTimeHelper.ToDateTime(value);
                }
            }
        }

        [DataMember]
        public int TotalInvoicesBilled { get; set; }

        [DataMember]
        public decimal TotalAmountBilled { get; set; }

        [DataMember]
        public int BilledHours { get; set; }

        [DataMember]
        public List<LineOfBusinessDetail> LineOfBusinessDetail { get; set; }

        [DataMember]
        public List<PayerDetail> PayerDetail { get; set; }

        [DataMember]
        public List<SubmissionTypeDetail> SubmissionTypeDetail { get; set; }

        [DataMember]
        public List<InvoiceDetail> BillingDetailServiceItems { get; set; }
    }
}