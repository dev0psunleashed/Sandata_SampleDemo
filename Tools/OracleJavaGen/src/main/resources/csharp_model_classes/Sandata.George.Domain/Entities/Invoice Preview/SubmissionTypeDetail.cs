using System;
using System.Collections.Generic;
using System.Runtime.Serialization;


namespace Sandata.George.Domain.Entities
{
    [DataContract]
    public class SubmissionTypeDetail
    {
        // https://mnt-ers-ts01.sandata.com/object/view.spg?key=154792
        [DataMember]
        public String InvoiceSubmissionTypeName { get; set; }

        [DataMember]
        public List<PayerDetail> PayerDetail { get; set; }
    }
}