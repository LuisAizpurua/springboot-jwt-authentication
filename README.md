<pre> ```
  __  __ _   _ _____ ___ ___  _____   __
 |  \/  | | | |_   _| __|   \| __\ \ / /
 | |\/| | |_| | | | | _|| |) | _| \ V / 
 |_|  |_|\___/  |_| |___|___/|___| \_/
```
</pre>
---
# 💻 Authenticate, Register And Logged In.
## 🚀 Spring Security & JWT.

<p>
🔥 How to work with JWT and manage roles within Java.
</p>

#### 🛡️ Encrypted passwords (for test)
 - `{ username: "carlos.45", password: "car_245" }`
 - `{ username: "felix.23god", password: "felort20_10" }`
 - `{ username: "moi054.ort", password: "moi_03ortdev" }`

##### 🌐 To view the database: `http://localhost:8090/h2-console`
##### 🔒 DATA H2:  `username: root | password: sasa`
##### 🌐 PORT: `8090`
##### 🙋‍♂️ ROLES USER: `ADMIN, CUSTOMER & USER`

#### 🧾 Deployed enpoints 
  - ##### 📦 For Products
```java
   // - /product/all (GET)
   // - /product/{id} (GET)
   // - /product/update (PUT)
   // - /product/save (POST)
   // - /product/{id}/delete (DELETE)
```  
  - ##### 🛡️ ️️For Authentication
```java
    // - /auth/register (POST)
    // - /auth/validate/{jwt} (POST)
    // - /auth/authenticate (POST)
    // - /auth/logged (GET)
```

#### ⚙️ Raise the project
```java
 // ./mvnw.cmd clean package -DskipTests && java -jar ./target/security-0.0.1-SNAPSHOT.jar
```

#### 📦 Tech Stack

- Spring Boot
- JPA & Hibernate
- H2 
- JJWT Jackson
- Lombok

#### 🐳 With Docker

##### Raise the docker compose
`docker compose -f ./security/docker-compose.yml up -d`

##### 🐳 Build Image
`docker build -t mute-security .`
`docker run --env-file .env -p 8096:8090 -d --rm mute-security`

<div style="font-weight: 700; margin: 15px 0; text-align: center; font-size: 20px; color: purple; text-decoration: underline;">
    It Was A Small Example Of Spring Security Safety To Test.
</div>

