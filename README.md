# XSD2XML
Generation xml from xsd (#java #jlibs #xsd #xml)

### Xsd
```xsd
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema">

<xs:element name="person">
  <xs:complexType>
    <xs:choice>
      <xs:element name="employee" type="Employee"/>
      <xs:element name="member" type="Member"/>
    </xs:choice>
  </xs:complexType>
</xs:element> 

<xs:complexType name="Employee">
  <xs:sequence>
    <xs:element name="name" type="xs:string"/>
    <xs:element name="age" type="xs:int"/>
    <xs:element name="hobby">
        <xs:complexType>
          <xs:choice>
            <xs:element name="cpp" type="xs:string"/>
            <xs:element name="java" type="xs:string"/>
            <xs:element name="c_sharp" type="xs:string"/>
            <xs:element name="other">
                <xs:complexType>
                    <xs:sequence>
                        <xs:element name="lang" type="xs:string" maxOccurs="unbounded"/>
                    </xs:sequence>
                </xs:complexType>
            </xs:element>
          </xs:choice>
        </xs:complexType>
  </xs:element> 
  </xs:sequence>
</xs:complexType>

<xs:complexType name="Member">
  <xs:sequence>
    <xs:element name="fio" type="xs:string"/>
    <xs:element name="weight" type="xs:int"/>
  </xs:sequence>
</xs:complexType>

</xs:schema>
```

### Xml
```xml
<!--(employee | member)-->
<person xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <member>
    <fio>fio1</fio>
    <weight>-1929585455</weight>
  </member>
  <employee>
    <name>name1</name>
    <age>-947455808</age>
    <!--(cpp | java | c_sharp | other)-->
    <hobby>
      <cpp>cpp1</cpp>
      <!--(lang+)-->
      <other>
        <lang>lang1</lang>
        <lang>lang2</lang>
      </other>
      <c_sharp>c_sharp1</c_sharp>
      <java>java1</java>
    </hobby>
  </employee>
</person>
```
