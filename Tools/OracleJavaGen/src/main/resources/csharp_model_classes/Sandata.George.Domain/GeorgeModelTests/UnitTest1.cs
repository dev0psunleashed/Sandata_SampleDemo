using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Sandata.George.Domain.Entities;
using FizzWare.NBuilder;
using System.IO;
using System.Runtime.Serialization.Json;

namespace GeorgeModelTests
{
    [TestClass]
    public class SerializationTests
    {
        [TestMethod]
        public void should_serialize_a_patient_object_to_json()
        {
            var dir = @"data\json";
            var path = dir + @"\patient.cs.json";

            if (!Directory.Exists(dir))
            {
                Directory.CreateDirectory(dir);
            }

            var patient = Builder<Patient>.CreateNew().Build();

            Assert.IsNotNull(patient);

            var memoryStream = new MemoryStream();
            DataContractJsonSerializer serializer = new DataContractJsonSerializer(typeof(Patient));
            serializer.WriteObject(memoryStream, patient);
            memoryStream.Position = 0;
            var streamReader = new StreamReader(memoryStream);
            var json = streamReader.ReadToEnd();
            File.WriteAllText(path, json);
        }
    }
}
