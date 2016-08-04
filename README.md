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
        <xs:attribute name="checked" type="xs:boolean"/>
    </xs:complexType>

    <xs:complexType name="Member">
        <xs:sequence>
            <xs:element name="fio" type="xs:string"/>
            <xs:element name="weight" type="xs:int"/>
        </xs:sequence>
        <xs:attribute name="birthday" type="xs:date"/>
    </xs:complexType>

</xs:schema>
```

### Xml
```xml
<!--(employee | member)-->
<person xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    <member birthday="2016-08-04">
        <fio>fio1</fio>
        <weight>432894162</weight>
    </member>
    <employee checked="false">
        <name>name1</name>
        <age>1072568470</age>
        <!--(cpp | java | c_sharp | other)-->
        <hobby>
            <cpp>cpp1</cpp>
            <c_sharp>c_sharp1</c_sharp>
            <java>java1</java>
            <!--(lang+)-->
            <other>
                <lang>lang1</lang>
                <lang>lang2</lang>
            </other>
        </hobby>
    </employee>
</person>
```
