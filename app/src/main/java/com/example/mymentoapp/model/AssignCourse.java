package com.example.mymentoapp.model;

import java.util.ArrayList;
import java.util.List;

public class AssignCourse {

    private String studyYear;
    private String domain;
    private String specialization;
    private List<SpecificCourse> specificCourseList;

    public AssignCourse(String studyYear, String domain, String[] specialization) {
        this.studyYear = studyYear;
        this.domain = domain;
        this.specialization = specialization[0];
        this.specificCourseList= new ArrayList<>();

        if(studyYear.equals("I")) {
            if (domain.equals("Informatics")) {

                specificCourseList.add(new SpecificCourse("Algorithmic Programming", "Algorithms, data structures, ideas for computer problems written in Pyhton"));
                specificCourseList.add(new SpecificCourse("Algebraic Structures in Informatics", "Introduction to algebraic notions applicable in programming"));
                specificCourseList.add(new SpecificCourse("Mathematical and Computational Logic", "About logical relations, classes of objects, logical reasoning"));
                specificCourseList.add(new SpecificCourse("Critical Thinking and Academic Ethics", "Mainly debates on current issues that concern us as programmers"));
                specificCourseList.add(new SpecificCourse("Differential and Integral Calculus", "Useful mathematical analysis course"));
                specificCourseList.add(new SpecificCourse("Computer Systems Architecture", "Course on low level programming"));

                specificCourseList.add(new SpecificCourse("Data Structures", "Course on the main data structures"));
                specificCourseList.add(new SpecificCourse("Data Bases", "Introductory course in databases"));
                specificCourseList.add(new SpecificCourse("OOP", "Course on the principles of object-oriented programming"));
                specificCourseList.add(new SpecificCourse("Web Techniques", "Basically HTML, CSS, JavaScript, NodeJs"));
                specificCourseList.add(new SpecificCourse("Geometry and Linear Algebra", "Course on the principles of vector geometry and linear algebra"));
                specificCourseList.add(new SpecificCourse("Formal and Automatic Languages", "Interesting course on formal and automatic languages, Noam Chomsky stuff"));

            } else if (domain.equals("CTI")) {
                specificCourseList.add(new SpecificCourse("Mathematical Analysis", "Numerical series, continuity"));
                specificCourseList.add(new SpecificCourse("Algebra and Geometry", "Introductory course in geometry and algebra"));
                specificCourseList.add(new SpecificCourse("Computer Programming", "Programming course"));
                specificCourseList.add(new SpecificCourse("Physics", "The wonderful physics at Magurele"));
                specificCourseList.add(new SpecificCourse("Logical Design", "You will learns the basics"));
                specificCourseList.add(new SpecificCourse("Critical Thinking and Academic Ethics", "Learning to speak in front of people and support your ideas"));
                specificCourseList.add(new SpecificCourse("Physical Education", "Let's learn to play a sport"));
                specificCourseList.add(new SpecificCourse("Special Mathematics", "Love maths"));
                specificCourseList.add(new SpecificCourse("Numerical calculation", "Love maths"));
                specificCourseList.add(new SpecificCourse("Programming Techniques", "Fundamental in informatics"));
                specificCourseList.add(new SpecificCourse("Computer Aided Design", "Something"));
                specificCourseList.add(new SpecificCourse("Use of Operating Systems", "Loved course because of Irofti"));
                specificCourseList.add(new SpecificCourse("The Basics of Electrical Engineering", "Other physics"));
                specificCourseList.add(new SpecificCourse("Specific Skills in a Foreign Language", "English or French"));
                specificCourseList.add(new SpecificCourse("Basic Skills in a Foreign Language", "English or French"));
            }
            else{
                specificCourseList.add(new SpecificCourse("Linear Algebra", "Basics and Advanced principles"));
                specificCourseList.add(new SpecificCourse("Algebra I", "The beginning"));
                specificCourseList.add(new SpecificCourse("Mathematical Analysis I", "Only the beginning"));
                specificCourseList.add(new SpecificCourse("Geometry I", "Only the beginning"));
                specificCourseList.add(new SpecificCourse("Procedural Programming", "C language"));
                specificCourseList.add(new SpecificCourse("Critical Thinking and Academic Ethics", "Learning to speak in front of people and support your ideas"));
                specificCourseList.add(new SpecificCourse("Specific Skills in a Foreign Language", "English or French"));
                specificCourseList.add(new SpecificCourse("Basic Skills in a Foreign Language", "English or French"));
                specificCourseList.add(new SpecificCourse("Physical and Methodical Education", "Easy"));
                specificCourseList.add(new SpecificCourse("Algebra II", "Continuation of the one from the first semester"));
                specificCourseList.add(new SpecificCourse("Mathematical Analysis II", "Continuation of the one from the first semester"));
                specificCourseList.add(new SpecificCourse("Geometry II", "Continuation of the one from the first semester"));
                specificCourseList.add(new SpecificCourse("Elements of Scientific Calculation", "Important course"));
                specificCourseList.add(new SpecificCourse("Mathematical Logic", "Very important course, both in math and informatics"));
            }
        }
        else if(studyYear.equals("II")){
            if(domain.equals("Informatics")){

                //Anul I
                specificCourseList.add(new SpecificCourse("Algorithmic Programming","Algorithms, data structures, ideas for computer problems written in Pyhton"));
                specificCourseList.add(new SpecificCourse("Algebraic Structures in Informatics", "Introduction to algebraic notions applicable in programming"));
                specificCourseList.add(new SpecificCourse("Mathematical and Computational Logic", "About logical relations, classes of objects, logical reasoning"));
                specificCourseList.add(new SpecificCourse("Critical Thinking and Academic Ethics", "Mainly debates on current issues that concern us as programmers"));
                specificCourseList.add(new SpecificCourse("Differential and Integral Calculus", "Useful mathematical analysis course"));
                specificCourseList.add(new SpecificCourse("Computer Systems Architecture", "Course on low level programming"));
                specificCourseList.add(new SpecificCourse("Data Bases", "Introductory course in databases"));
                specificCourseList.add(new SpecificCourse("OOP","Course on the principles of object-oriented programming"));
                specificCourseList.add(new SpecificCourse("Web Techniques","Basically HTML, CSS, JavaScript, NodeJs"));
                specificCourseList.add(new SpecificCourse("Data Structures","Course on the main data structures"));
                specificCourseList.add(new SpecificCourse("Geometry and Linear Algebra","Course on the principles of vector geometry and linear algebra"));
                specificCourseList.add(new SpecificCourse("Formal and Automatic Languages","Interesting course on formal "));


                //Anul II
                specificCourseList.add(new SpecificCourse("Optional Web Application Development ASP ", "Course on ASP.NET technologies"));
                specificCourseList.add(new SpecificCourse("Optional Web Application Development PHP","Course on PHP technologies "));
                specificCourseList.add(new SpecificCourse("Functional Programming","Course on programming languages, haskell syntax"));
                specificCourseList.add(new SpecificCourse("Probabilities and Statistics","Introduction to the principles of probabilistic field"));
                specificCourseList.add(new SpecificCourse("Fundamental Algorithms","Important basic algorithmic course"));
                specificCourseList.add(new SpecificCourse("Operating Systems","Course on how known operating systems work"));
                specificCourseList.add(new SpecificCourse("SGBD","Database management systems, continuation of the BD course"));
                specificCourseList.add(new SpecificCourse("Computer Networks","Principles of creating a topology of Local Area Networks"));
                specificCourseList.add(new SpecificCourse("Fundamentals of the Programming Language", "Continuation of the Functional Programming course, to which are added notions of Prologue"));
                specificCourseList.add(new SpecificCourse("Advanced Algorithms", "Continuation of the course of fundamental algorithms, also probabilistic and genetic algorithms"));
                specificCourseList.add(new SpecificCourse("Software Development Methods", "How to develop a mobile or web application"));
                specificCourseList.add(new SpecificCourse("Advanced Object Programming", "The next level after OOP, written in Java language"));
                specificCourseList.add(new SpecificCourse("Artificial Intelligence", "Course on machine learning and knowledge representation"));

            }
            else if (domain.equals("Mathematics")) {
                specificCourseList.add(new SpecificCourse("Linear Algebra", "Basics and Advanced principles"));
                specificCourseList.add(new SpecificCourse("Algebra I", "The beginning"));
                specificCourseList.add(new SpecificCourse("Mathematical Analysis I", "Only the beginning"));
                specificCourseList.add(new SpecificCourse("Geometry I", "Only the beginning"));
                specificCourseList.add(new SpecificCourse("Procedural Programming", "C language"));
                specificCourseList.add(new SpecificCourse("Critical Thinking and Academic Ethics", "Learning to speak in front of people and support your ideas"));
                specificCourseList.add(new SpecificCourse("Specific Skills in a Foreign Language", "English or French"));
                specificCourseList.add(new SpecificCourse("Basic Skills in a Foreign Language", "English or French"));
                specificCourseList.add(new SpecificCourse("Physical and Methodical Education", "Easy"));
                specificCourseList.add(new SpecificCourse("Algebra II", "Continuation of the one from the first semester"));
                specificCourseList.add(new SpecificCourse("Mathematical Analysis II", "Continuation of the one from the first semester"));
                specificCourseList.add(new SpecificCourse("Geometry II", "Continuation of the one from the first semester"));
                specificCourseList.add(new SpecificCourse("Elements of Scientific Calculation", "Important course"));
                specificCourseList.add(new SpecificCourse("Mathematical Logic", "Very important course, both in math and informatics"));
                specificCourseList.add(new SpecificCourse("Algebra III","Next level of Algebra III"));
                specificCourseList.add(new SpecificCourse("Algebra IV", "Older brother of Algebra IV"));
                specificCourseList.add(new SpecificCourse("Differential Geometry I", "Some good geometry stuff"));
                specificCourseList.add(new SpecificCourse("Theory of Measure and Integration", "Best theory you'll ever learn"));
                specificCourseList.add(new SpecificCourse("Differential Equations", "Some nice calculus here"));
                specificCourseList.add(new SpecificCourse("Complex Analysis", "Not that complex tho"));
                specificCourseList.add(new SpecificCourse("Advanced Skills in a Foreign Language I", "English, German, Latin..."));
                specificCourseList.add(new SpecificCourse("Advanced Skills in a Foreign Language II", "English, German, Latin..."));
                specificCourseList.add(new SpecificCourse("Probabilities", "So you can calculate the probability to pass the exam"));

                if (specialization.equals("Pure Mathematics")) {

                    specificCourseList.add(new SpecificCourse("Number Theory", "Another great theory you'll learn"));
                    specificCourseList.add(new SpecificCourse("Differential Geometry II", "Some more good geometry stuff"));
                    specificCourseList.add(new SpecificCourse("Functional Analysis", "Nice working analysis"));
                    specificCourseList.add(new SpecificCourse("Mathematical Modeling of Material Systems I", "Title speaks for itself"));

                }
                else if (specialization.equals("Applied Mathematics")){

                    specificCourseList.add(new SpecificCourse("Mathematical Modeling of Material Systems I", "Title speaks for itself"));
                    specificCourseList.add(new SpecificCourse("Operational Research","Study the problems of\n" +
                            "optimization from the mathematical modeling of some phenomena and processes in the field\n" +
                            "economic, scientific, technical or military"));
                    specificCourseList.add(new SpecificCourse("Cryptography ","Optional - Operation of the main symmetric and key crypto systems\\n\" +\n" +
                            "                        \"publishes as well as some methods of their attack\""));
                    specificCourseList.add(new SpecificCourse("Fundamental Algorithms ","Optional - Important basic algorithmic course"));
                    specificCourseList.add(new SpecificCourse("Heavenly Mechanics", "Angel stuff"));
                    specificCourseList.add(new SpecificCourse("Physics", "Basic course of physics"));
                    specificCourseList.add(new SpecificCourse("Mathematical Software", "One great course about how mathematics work in software"));
                    specificCourseList.add(new SpecificCourse("Artificial Intelligence", "Course on machine learning and knowledge representation"));

                }
                else {

                    specificCourseList.add(new SpecificCourse("Computer Systems Architecture", "Optional - Course on low level programming"));
                    specificCourseList.add(new SpecificCourse("Operating Systems","Optional - Course on how known operating systems work"));
                    specificCourseList.add(new SpecificCourse("OOP","Course on the principles of object-oriented programming"));
                    specificCourseList.add(new SpecificCourse("Web Techniques", "Basically HTML, CSS, JavaScript, NodeJs"));
                    specificCourseList.add(new SpecificCourse("Data Structures", "Course on the main data structures"));
                    specificCourseList.add(new SpecificCourse("Data Bases", "Introductory course in databases"));
                }
            }
            else {

                //Anul 1
                specificCourseList.add(new SpecificCourse("Mathematical Analysis", "Numerical series, continuity"));
                specificCourseList.add(new SpecificCourse("Algebra and Geometry", "Introductory course in geometry and algebra"));
                specificCourseList.add(new SpecificCourse("Computer Programming", "Programming course"));
                specificCourseList.add(new SpecificCourse("Physics", "The wonderful physics at Magurele"));
                specificCourseList.add(new SpecificCourse("Logical Design", "You will learns the basics"));
                specificCourseList.add(new SpecificCourse("Critical Thinking and Academic Ethics", "Learning to speak in front of people and support your ideas"));
                specificCourseList.add(new SpecificCourse("Physical Education", "Let's learn to play a sport"));
                specificCourseList.add(new SpecificCourse("Special Mathematics", "Love maths"));
                specificCourseList.add(new SpecificCourse("Numerical Calculation", "Love maths"));
                specificCourseList.add(new SpecificCourse("Programming Techniques", "Fundamental in informatics"));
                specificCourseList.add(new SpecificCourse("Computer Aided Design", "Something"));
                specificCourseList.add(new SpecificCourse("Use of Operating Systems", "Loved course because of Irofti"));
                specificCourseList.add(new SpecificCourse("The Basics of Electrical Engineering", "Other physics"));
                specificCourseList.add(new SpecificCourse("Specific Skills in a Foreign Language", "English or French"));
                specificCourseList.add(new SpecificCourse("Basic Skills in a Foreign Language", "English or French"));


                //anul 2
                specificCourseList.add(new SpecificCourse("Data Structures and Algorithms", "The best course for everyone") );
                specificCourseList.add(new SpecificCourse("Object Oriented Programming", "The base of every programmer"));
                specificCourseList.add(new SpecificCourse("Database", "Fundamental and Advanced principles which everyone must know"));
                specificCourseList.add(new SpecificCourse("Fundamentals of Computer Networks", "Networking base: IP, routers, switches"));
                specificCourseList.add(new SpecificCourse("Elements of Analog Electronics", "Something"));
                specificCourseList.add(new SpecificCourse("Systems Theory", "Great course"));
                specificCourseList.add(new SpecificCourse("Advanced Skills in a Foreign Language I", "English or French"));
                specificCourseList.add(new SpecificCourse("Probability and Statistics", "Fundamental course, useful in Artificial Intelligence"));
                specificCourseList.add(new SpecificCourse("Advanced Programming Elements", "Another great course for every future programmer"));
                specificCourseList.add(new SpecificCourse("Fundamentals of Network Routing", "Great for everyone who wants to know more about networking"));
                specificCourseList.add(new SpecificCourse("Digital Electronics", "Physics power"));
                specificCourseList.add(new SpecificCourse("Digital Computers", "Perfect for future great engineers"));
                specificCourseList.add(new SpecificCourse("The Management and Career Guidance", "Useful for every student"));
                specificCourseList.add(new SpecificCourse("Advanced Skills in a Foreign Language II", "English or French"));

            }
        }
        else if(studyYear.equals("III")){
            if(domain.equals("Informatics")){

                specificCourseList.add(new SpecificCourse("Algorithmic Programming","Algorithms, data structures, ideas for computer problems written in Pyhton"));
                specificCourseList.add(new SpecificCourse("Algebraic Structures in Informatics", "Introduction to algebraic notions applicable in programming"));
                specificCourseList.add(new SpecificCourse("Mathematical and Computational Logic", "About logical relations, classes of objects, logical reasoning"));
                specificCourseList.add(new SpecificCourse("Critical Thinking and Academic Ethics", "Mainly debates on current issues that concern us as programmers"));
                specificCourseList.add(new SpecificCourse("Differential and Integral Calculus", "Useful mathematical analysis course"));
                specificCourseList.add(new SpecificCourse("Computer Systems Architecture", "Course on low level programming"));
                specificCourseList.add(new SpecificCourse("Data Bases", "Introductory course in databases"));
                specificCourseList.add(new SpecificCourse("OOP","Course on the principles of object-oriented programming"));
                specificCourseList.add(new SpecificCourse("Web Techniques","Basically HTML, CSS, JavaScript, NodeJs"));
                specificCourseList.add(new SpecificCourse("Data Structures","Course on the main data structures"));
                specificCourseList.add(new SpecificCourse("Geometry and Linear Algebra","Course on the principles of vector geometry and linear algebra"));
                specificCourseList.add(new SpecificCourse("Formal and Automatic Languages","Interesting course on formal "));


                specificCourseList.add(new SpecificCourse("Optional Web Application Development ASP ", "Course on ASP.NET technologies"));
                specificCourseList.add(new SpecificCourse("Optional Web Application Development PHP","Course on PHP technologies "));
                specificCourseList.add(new SpecificCourse("Functional Programming","Course on programming languages, haskell syntax"));
                specificCourseList.add(new SpecificCourse("Probabilities and Statistics","Introduction to the principles of probabilistic field"));
                specificCourseList.add(new SpecificCourse("Fundamental Algorithms","Important basic algorithmic course"));
                specificCourseList.add(new SpecificCourse("Operating Systems","Course on how known operating systems work"));
                specificCourseList.add(new SpecificCourse("SGBD","Database management systems, continuation of the BD course"));
                specificCourseList.add(new SpecificCourse("Computer Networks","Principles of creating a topology of Local Area Networks"));
                specificCourseList.add(new SpecificCourse("Fundamentals of the Programming Language", "Continuation of the Functional Programming course, to which are added notions of Prologue"));
                specificCourseList.add(new SpecificCourse("Advanced Algorithms", "Continuation of the course of fundamental algorithms, also probabilistic and genetic algorithms"));
                specificCourseList.add(new SpecificCourse("Software Development Methods", "How to develop a mobile or web application"));
                specificCourseList.add(new SpecificCourse("Advanced Object Programming", "The next level after OOP, written in Java language"));
                specificCourseList.add(new SpecificCourse("Artificial Intelligence", "Course on machine learning and knowledge representation"));


                specificCourseList.add(new SpecificCourse("Numerical Analysis","Course on numerical analysis in mathematics"));
                specificCourseList.add(new SpecificCourse("Declarative Programming","Next level of Programming Language Course"));
                specificCourseList.add(new SpecificCourse("Web Application Development","Title speaks for itself"));
                specificCourseList.add(new SpecificCourse("Differential and Partial Differential Equations","Important mathematical concepts"));
                specificCourseList.add(new SpecificCourse("Database Management Systems","Database management systems, continuation of the SGBD course"));
                specificCourseList.add(new SpecificCourse("Simulation Techniques","Title speaks for itself"));
                specificCourseList.add(new SpecificCourse("Optimization Techniques","Optimization course for algorithms and good practice in programming"));
                specificCourseList.add(new SpecificCourse("Programming Engineering","Design Models, ULM diagrams, Maintenance, etc"));
                specificCourseList.add(new SpecificCourse("Cryptography and Security","Operation of the main symmetric and key crypto systems\\n\" +\n" +
                        "                        \"publishes as well as some methods of their attack\""));
                specificCourseList.add(new SpecificCourse("Compilation techniques","Title speaks for itself"));


            }
            else if(domain.equals("Mathematics")){
                specificCourseList.add(new SpecificCourse("Linear Algebra", "Basics and Advanced principles"));
                specificCourseList.add(new SpecificCourse("Algebra I", "The beginning"));
                specificCourseList.add(new SpecificCourse("Mathematical Analysis I", "Only the beginning"));
                specificCourseList.add(new SpecificCourse("Geometry I", "Only the beginning"));
                specificCourseList.add(new SpecificCourse("Procedural Programming", "C language"));
                specificCourseList.add(new SpecificCourse("Critical Thinking and Academic Ethics", "Learning to speak in front of people and support your ideas"));
                specificCourseList.add(new SpecificCourse("Specific Skills in a Foreign Language", "English or French"));
                specificCourseList.add(new SpecificCourse("Basic Skills in a Foreign Language", "English or French"));
                specificCourseList.add(new SpecificCourse("Physical and Methodical Education", "Easy"));
                specificCourseList.add(new SpecificCourse("Algebra II", "Continuation of the one from the first semester"));
                specificCourseList.add(new SpecificCourse("Mathematical Analysis II", "Continuation of the one from the first semester"));
                specificCourseList.add(new SpecificCourse("Geometry II", "Continuation of the one from the first semester"));
                specificCourseList.add(new SpecificCourse("Elements of Scientific Calculation", "Important course"));
                specificCourseList.add(new SpecificCourse("Mathematical Logic", "Very important course, both in math and informatics"));
                specificCourseList.add(new SpecificCourse("Algebra III","Next level of Algebra III"));
                specificCourseList.add(new SpecificCourse("Algebra IV", "Older brother of Algebra IV"));
                specificCourseList.add(new SpecificCourse("Theory of Measure and Integration", "Best theory you'll ever learn"));
                specificCourseList.add(new SpecificCourse("Differential Equations", "Some nice calculus here"));
                specificCourseList.add(new SpecificCourse("Complex Analysis", "Not that complex tho"));
                specificCourseList.add(new SpecificCourse("Advanced Skills in a Foreign Language I", "English, German, Latin..."));
                specificCourseList.add(new SpecificCourse("Advanced Skills in a Foreign Language II", "English, German, Latin..."));
                specificCourseList.add(new SpecificCourse("Probabilities", "So you can calculate the probability to pass the exam"));
                specificCourseList.add(new SpecificCourse("Statistics", "The best"));
                if(specialization.equals("Applied Mathematics")){

                    specificCourseList.add(new SpecificCourse("Differential Geometry I", "Some good geometry stuff"));
                    specificCourseList.add(new SpecificCourse("Numeric analysis", "Maths love"));
                    specificCourseList.add(new SpecificCourse("Partial Differential Equations", "Maths love"));
                    specificCourseList.add(new SpecificCourse("Astronomy", "Planets"));
                    specificCourseList.add(new SpecificCourse("Mechanics of Continuous Media", "Engineering stuff"));

                }

                else if(specialization.equals("Mathematics-Informatics")){
                    specificCourseList.add(new SpecificCourse("Differential Geometry I", "Some good geometry stuff"));
                    specificCourseList.add(new SpecificCourse("Numerical Analysis","Course on numerical analysis in mathematics"));
                    specificCourseList.add(new SpecificCourse("Partial Derivative Equations","Nice calculus"));
                    specificCourseList.add(new SpecificCourse("Operational Research","Study the problems of\n" +
                            "optimization from the mathematical modeling of some phenomena and processes in the field\n" +
                            "economic, scientific, technical or military"));
                    specificCourseList.add(new SpecificCourse("Web Techniques","Basically HTML, CSS, JavaScript, NodeJs"));
                    specificCourseList.add(new SpecificCourse("Number Theory With Applications in Cryptography", "Great crypto course"));
                    specificCourseList.add(new SpecificCourse("Computer Networks","Principles of creating a topology of Local Area Networks"));
                    specificCourseList.add(new SpecificCourse("Artificial Intelligence", "Maybe the most wanted and useful nowadays"));
                    specificCourseList.add(new SpecificCourse("Logical Programming", "Logical stuff"));
                    specificCourseList.add(new SpecificCourse("Mathematics", "Optional course"));
                    specificCourseList.add(new SpecificCourse("Informatics", "Optional course"));

                }
                else{
                    specificCourseList.add(new SpecificCourse("Differential Geometry", "Maths love"));
                    specificCourseList.add(new SpecificCourse("Functional Analysis", "Functional stuff"));
                    specificCourseList.add(new SpecificCourse("Partial Differential Equations", "Maths love"));
                    specificCourseList.add(new SpecificCourse("Numeric Analysis", "Maths stuff"));
                    specificCourseList.add(new SpecificCourse("Elements of Modern Algebra", "Algebra stuff"));
                    specificCourseList.add(new SpecificCourse("Geometry Complements", "Geometry stuff"));
                    specificCourseList.add(new SpecificCourse("Astronomy", "Planets"));

                }

            }
            else {

                //Anul 1
                specificCourseList.add(new SpecificCourse("Mathematical Analysis", "Numerical series, continuity"));
                specificCourseList.add(new SpecificCourse("Algebra and Geometry", "Introductory course in geometry and algebra"));
                specificCourseList.add(new SpecificCourse("Computer Programming", "Programming course"));
                specificCourseList.add(new SpecificCourse("Physics", "The wonderful physics at Magurele"));
                specificCourseList.add(new SpecificCourse("Logical Design", "You will learns the basics"));
                specificCourseList.add(new SpecificCourse("Critical Thinking and Academic Ethics", "Learning to speak in front of people and support your ideas"));
                specificCourseList.add(new SpecificCourse("Physical Education", "Let's learn to play a sport"));
                specificCourseList.add(new SpecificCourse("Special Mathematics", "Love maths"));
                specificCourseList.add(new SpecificCourse("Numerical Calculation", "Love maths"));
                specificCourseList.add(new SpecificCourse("Programming Techniques", "Fundamental in informatics"));
                specificCourseList.add(new SpecificCourse("Computer Aided Design", "Something"));
                specificCourseList.add(new SpecificCourse("Use of Operating Systems", "Loved course because of Irofti"));
                specificCourseList.add(new SpecificCourse("The Basics of Electrical Engineering", "Other physics"));
                specificCourseList.add(new SpecificCourse("Specific Skills in a Foreign Language", "English or French"));
                specificCourseList.add(new SpecificCourse("Basic Skills in a Foreign Language", "English or French"));


                //anul 2
                specificCourseList.add(new SpecificCourse("Data Structures and Algorithms", "The best course for everyone") );
                specificCourseList.add(new SpecificCourse("Object Oriented Programming", "The base of every programmer"));
                specificCourseList.add(new SpecificCourse("Database", "Fundamental and Advanced principles which everyone must know"));
                specificCourseList.add(new SpecificCourse("Fundamentals of Computer Networks", "Networking base: IP, routers, switches"));
                specificCourseList.add(new SpecificCourse("Elements of Analog Electronics", "Something"));
                specificCourseList.add(new SpecificCourse("Systems Theory", "Great course"));
                specificCourseList.add(new SpecificCourse("Advanced Skills in a Foreign Language I", "English or French"));
                specificCourseList.add(new SpecificCourse("Probability and Statistics", "Fundamental course, useful in Artificial Intelligence"));
                specificCourseList.add(new SpecificCourse("Advanced Programming Elements", "Another great course for every future programmer"));
                specificCourseList.add(new SpecificCourse("Fundamentals of Network Routing", "Great for everyone who wants to know more about networking"));
                specificCourseList.add(new SpecificCourse("Digital Electronics", "Physics power"));
                specificCourseList.add(new SpecificCourse("Digital Computers", "Perfect for future great engineers"));
                specificCourseList.add(new SpecificCourse("The Management and Career Guidance", "Useful for every student"));
                specificCourseList.add(new SpecificCourse("Advanced Skills in a Foreign Language II", "English or French"));


                //anul 3
                specificCourseList.add(new SpecificCourse("Database Design", "Wonderful"));
                specificCourseList.add(new SpecificCourse("Operating Systems", "Very useful and great if you will have sir Irofti as teacher"));
                specificCourseList.add(new SpecificCourse("Artificial Intelligence", "Maybe the most wanted and useful nowadays"));
                specificCourseList.add(new SpecificCourse("Computer Systems Architecture", "Mister Dragulici the best"));
                specificCourseList.add(new SpecificCourse("Communications and Public relations", "Useful for work"));
                specificCourseList.add(new SpecificCourse("Management", "Useful for work place"));
                specificCourseList.add(new SpecificCourse("Cloud Computing", "Very used nowadays in industry"));
                specificCourseList.add(new SpecificCourse("Web Techniques", "HTML, CSS, JavaScript"));
                specificCourseList.add(new SpecificCourse("RPA Introduction", "Basics of RPA"));
                specificCourseList.add(new SpecificCourse("Computer Networks Scaling", "Other network course"));
                specificCourseList.add(new SpecificCourse("Cryptography and Security", "Every programmer must know"));
                specificCourseList.add(new SpecificCourse("Programming on Mobile Devices", "Android, iOs"));
                specificCourseList.add(new SpecificCourse("Learning Deep Neuronal Networks", "Advanced Artificial Intelligence"));
                specificCourseList.add(new SpecificCourse("Operations Management", "For future work place"));
                specificCourseList.add(new SpecificCourse("Marketing", "Some economy in informatics"));
                specificCourseList.add(new SpecificCourse("Computer Graphics", "Engineering course, useful for everyone"));
                specificCourseList.add(new SpecificCourse("Software Development Methods", "The best for the industry"));
                specificCourseList.add(new SpecificCourse("Microprocessor Systems", "Future engineer course"));
                specificCourseList.add(new SpecificCourse("Industrial Practice", "Compulsory Practice"));


            }
        }
        else{

            specificCourseList.add(new SpecificCourse("Distributed Systems", "How to distribute systems"));
            specificCourseList.add(new SpecificCourse("Programming Engineering", "Good for every future engineer"));
            specificCourseList.add(new SpecificCourse("Web Application Development", "ASP.NET or PHP"));
            specificCourseList.add(new SpecificCourse("Electronic Commerce", "How to sale online"));
            specificCourseList.add(new SpecificCourse("Cloud Computing", "Very used nowadays in industry"));
            specificCourseList.add(new SpecificCourse("Web Techniques", "HTML, CSS, JavaScript"));
            specificCourseList.add(new SpecificCourse("RPA Introduction", "Basics of RPA"));
            specificCourseList.add(new SpecificCourse("Computer Networks Scaling", "Other network course"));
            specificCourseList.add(new SpecificCourse("Cryptography and Security", "Every programmer must know"));
            specificCourseList.add(new SpecificCourse("Programming on Mobile Devices", "Android, iOs"));
            specificCourseList.add(new SpecificCourse("Learning deep neuronal networks", "Advanced Artificial Intelligence"));
            specificCourseList.add(new SpecificCourse("Modern Calculation and Simulation Methods", "Great"));
            specificCourseList.add(new SpecificCourse("Signals Processing", "Great course about signals"));
            specificCourseList.add(new SpecificCourse("Connecting Computer Networks at WAN Level", "Another networking course"));
            specificCourseList.add(new SpecificCourse("Operations Management", "For future work place"));
            specificCourseList.add(new SpecificCourse("Marketing", "Some economy in informatics"));
            specificCourseList.add(new SpecificCourse("Linux Operating System", "By Mister Irofti"));
            specificCourseList.add(new SpecificCourse("Parallel Algorithms", "Something about algorithms"));
            specificCourseList.add(new SpecificCourse("Compilers and Translators", "About compilers"));
            specificCourseList.add(new SpecificCourse("Software Systems Testing", "About industry"));
            specificCourseList.add(new SpecificCourse("Preparation of the Diploma Project", "About the final"));

            //anul 2            specificCourseList.add(new SpecificCourse("Mathematical Analysis", "Numerical series, continuity"));
            specificCourseList.add(new SpecificCourse("Algebra and Geometry", "Introductory course in geometry and algebra"));
            specificCourseList.add(new SpecificCourse("Computer Programming", "Programming course"));
            specificCourseList.add(new SpecificCourse("Physics", "The wonderful physics at Magurele"));
            specificCourseList.add(new SpecificCourse("Logical Design", "You will learns the basics"));
            specificCourseList.add(new SpecificCourse("Critical Thinking and Academic Ethics", "Learning to speak in front of people and support your ideas"));
            specificCourseList.add(new SpecificCourse("Physical Education", "Let's learn to play a sport"));
            specificCourseList.add(new SpecificCourse("Special Mathematics", "Love maths"));
            specificCourseList.add(new SpecificCourse("Numerical calculation", "Love maths"));
            specificCourseList.add(new SpecificCourse("Programming Techniques", "Fundamental in informatics"));
            specificCourseList.add(new SpecificCourse("Computer Aided Design", "Something"));
            specificCourseList.add(new SpecificCourse("Use of Operating Systems", "Loved course because of Irofti"));
            specificCourseList.add(new SpecificCourse("The Basics of Electrical Engineering", "Other physics"));
            specificCourseList.add(new SpecificCourse("Specific Skills in a Foreign Language", "English or French"));
            specificCourseList.add(new SpecificCourse("Basic Skills in a Foreign Language", "English or French"));

            //anul 2            specificCourseList.add(new SpecificCourse("Data Structures and Algorithms", "The best course for everyone") );
            specificCourseList.add(new SpecificCourse("Object Oriented Programming", "The base of every programmer"));
            specificCourseList.add(new SpecificCourse("Database", "Fundamental and Advanced principles which everyone must know"));
            specificCourseList.add(new SpecificCourse("Fundamentals of Computer Networks", "Networking base: IP, routers, switches"));
            specificCourseList.add(new SpecificCourse("Elements of Analog Electronics", "Something"));
            specificCourseList.add(new SpecificCourse("Systems Theory", "Great course"));
            specificCourseList.add(new SpecificCourse("Advanced Skills in a Foreign Language I", "English or French"));
            specificCourseList.add(new SpecificCourse("Probability and Statistics", "Fundamental course, useful in Artificial Intelligence"));
            specificCourseList.add(new SpecificCourse("Advanced Programming Elements", "Another great course for every future programmer"));
            specificCourseList.add(new SpecificCourse("Fundamentals of Network Routing", "Great for everyone who wants to know more about networking"));
            specificCourseList.add(new SpecificCourse("Digital Electronics", "Physics power"));
            specificCourseList.add(new SpecificCourse("Digital Computers", "Perfect for future great engineers"));
            specificCourseList.add(new SpecificCourse("The Management and Career Guidance", "Useful for every student"));
            specificCourseList.add(new SpecificCourse("Advanced Skills in a Foreign Language II", "English or French"));
            //anul 3

            specificCourseList.add(new SpecificCourse("Database Design", "Wonderful"));
            specificCourseList.add(new SpecificCourse("Operating Systems", "Very useful and great if you will have sir Irofti as teacher"));
            specificCourseList.add(new SpecificCourse("Artificial Intelligence", "Maybe the most wanted and useful nowadays"));
            specificCourseList.add(new SpecificCourse("Computer Systems Architecture", "Mister Dragulici the best"));
            specificCourseList.add(new SpecificCourse("Communications and Public relations", "Useful for work"));
            specificCourseList.add(new SpecificCourse("Management", "Useful for work place"));
            specificCourseList.add(new SpecificCourse("Computer Networks Scaling", "Other network course"));
            specificCourseList.add(new SpecificCourse("Cryptography and Security", "Every programmer must know"));
            specificCourseList.add(new SpecificCourse("Computer Graphics", "Engineering course, useful for everyone"));
            specificCourseList.add(new SpecificCourse("Software Development Methods", "The best for the industry"));
            specificCourseList.add(new SpecificCourse("Microprocessor Systems", "Future engineer course"));
            specificCourseList.add(new SpecificCourse("Industrial Practice", "Compulsory Practice"));

        }

    }


    public String getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(String studyYear) {
        this.studyYear = studyYear;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public List<SpecificCourse> getSpecificCourseList() {
        return specificCourseList;
    }

    public void setSpecificCourseList(List<SpecificCourse> specificCourseList) {
        this.specificCourseList = specificCourseList;
    }

    public List<SpecificCourse> getCourseToTeachList() { return specificCourseList; }
}