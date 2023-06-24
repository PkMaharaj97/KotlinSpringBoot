# KotlinSpringBoot
Creating the Apis using SpringBoot and Kotlin and storing data in Mysql database


Ther Are Two Tables
1.Printers
2.Books

# CRUD Apis for Books Tables
# Adding Book
POST--http://localhost:8080/api/books
request:{
    "id": null,
    "name": "Android fundamentals",
    "category": "Development",
    "author": "Andy Rubin",
    "printerId": 1
}

response:{
    "id": 4,
    "name": "Android fundamentals",
    "category": "Development",
    "author": "Andy Rubin",
    "printerId": 1
}


#Updating Book
PUT--http://localhost:8080/api/books/2

request:{"id":null, "name":"Java Fundementals", "category": "Development","author":"Dennis retche","Printer_ID":1}

response:{
    "id": 2,
    "name": "Java Fundementals",
    "category": "Development",
    "author": "Dennis retche",
    "printerId": null
}

# Reading Books

GET--http://localhost:8080/api/books

response:[
{
        "id": 2,
        "name": "Java fundamentals",
        "category": "Development",
        "author": "Dennis",
        "printerId": null
    },
    {
        "id": 3,
        "name": "Kotlin fundamentals",
        "category": "Development",
        "author": "JetBrains",
        "printerId": null
    },
    {
        "id": 4,
        "name": "Android fundamentals",
        "category": "Development",
        "author": "Andy Rubin",
        "printerId": null
    }
]


# Delete Book
Delete--http://localhost:8080/api/books/2

