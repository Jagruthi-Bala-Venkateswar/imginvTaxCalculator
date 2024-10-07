# Employee Tax Calculator API

## Overview

The Employee Tax Calculator API is a RESTful web service developed in Java using Spring Boot. The application allows you to store employee details and calculate their tax deductions based on specified salary slabs for the current financial year (April to March).

## Features

- **Store Employee Details**: Save employee information including ID, name, email, phone numbers, date of joining, and salary.
- **Calculate Tax Deductions**: Retrieve tax deduction information for employees based on their salary and date of joining.
- **Validation**: Ensure all employee fields are validated before storage.

## Technology Stack

- **Java 17**
- **Spring Boot 3.3.4**
- **Spring Data JPA**
- **H2 Database** (for in-memory storage)
- **SLF4J** (for logging)

## Prerequisites

- JDK 17
- Maven
- IDE (e.g., IntelliJ IDEA)

## Setup Instructions

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/tax-calculator.git
   cd tax-calculator
