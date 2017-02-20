using System;
using System.Runtime.Serialization;


namespace Sandata.George.Domain.Entities
{
    [DataContract]
    public class PayerDetail
    {
        [DataMember]
        public String PayerID { get; set; }
        
        [DataMember]
        public String PayerName { get; set; }

        [DataMember]
        public int TotalInvoicesBilled { get; set; }

        [DataMember]
        public decimal TotalAmountBilled { get; set; }

        [DataMember]
        public int BilledHours { get; set; }
    }
}