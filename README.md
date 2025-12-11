# **CS334 – Information Security Project**

![Status](https://img.shields.io/badge/Project-Completed-brightgreen)
![Course](https://img.shields.io/badge/Course-CS334-blue)
![SEEDLabs](https://img.shields.io/badge/Labs-SEED-orange)
![Language](https://img.shields.io/badge/Language-Java%20%7C%20OpenSSL-lightgrey)

---

## **Overview**
This repository contains a practical cryptography project developed for the Information Security course (CS334).  
The project applies encryption algorithms and modes using SEED Labs, and includes a Java dictionary attack to test weak keys.

---

## **Tasks**

### **Task 2 – Text Encryption**
Algorithms implemented:
- AES-128-CBC  
- AES-128-CFB  
- Blowfish-CBC  

Includes plaintext, ciphertext outputs, and a verified decrypted file.

---

### **Task 3 – Image Encryption (ECB vs CBC)**
- **ECB:** visible patterns remain → insecure  
- **CBC:** image fully concealed → secure  

Includes BMP header/body components and encrypted outputs.

---

### **Task 7 – Java Dictionary Attack**
A Java program that tests multiple keys from a wordlist to recover the correct key.  
Demonstrates why predictable or weak keys are vulnerable.

---

## **Repository Structure**

Task2/ → plaintext + AES/Blowfish outputs + decrypted file
Task3/ → header, body_ecb, body_cbc
Task7/ → Java code + words.txt
screenshots/ → SEED Labs screenshots
docs/ → optional report


---

## **Summary**
This project demonstrates:
- Differences between encryption modes  
- The insecurity of ECB  
- The importance of strong keys  
- Practical hands-on understanding of applied cryptography  


