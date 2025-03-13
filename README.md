## 🌟 Blog Application with Firebase

A dynamic and user-friendly **Blog Application** built with modern web technologies. This platform enables users to create, manage, and explore engaging blog posts, offering seamless functionality and an elegant design. Currently Blogs can be uploaded by Admins only while Users can view and comment on the blogs.

---

## 🖥️ Features

- **User-Friendly Interface**: Intuitive and responsive design for all devices.
- **Authentication**:
  - Login/Logout functionality.
  - Role-based access (Admin/User).
- **Blog Management**:
  - Create, update, and delete blog posts.
  - Display blogs with proper formatting and conditional image rendering.
- **Dashboard**:
  - Lists all blogs with titles, authors, excerpts, and "Read More" links.
  - Automatically handles anonymous users and login prompts.
- **15+ Categories to Upload Blogs**
- **Searching & Sorting Feature (Latest, Oldest, Most Viewed)**
- **User Comments & View Count**
- **Thymeleaf Integration**:
  - Dynamic server-side rendering with Thymeleaf.
- **Secure Access**:
  - Authorization checks ensure that only authenticated users can access certain pages.

---

## 🛠️ Tech Stack

- **Frontend**:
  - HTML5, CSS3
  - Thymeleaf (for server-side templating)
  - Font Awesome (for icons)
- **Backend**:
  - Java Spring Boot
- **Database**:
  - MySQL
- **Build Tool**:
  - Maven
- **Spring Security**

---

## 🚀 Installation and Setup

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Programmer-govind/BlogWithFirebase.git
   cd BlogWithFireBase
   ```

2. **Configure the Database**:
   - Create a database in MySQL (e.g., `blog_app`).
   - Update the database credentials in `application.properties`:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/blog_app
     spring.datasource.username=your-username
     spring.datasource.password=your-password
     ```

3. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```

4. **Access the Application**:
   Open your browser and navigate to:
   ```
   http://localhost:9595
   ```

---

## 📸 Screenshots

### Dashboard
![Dashboard Screenshot](https://github.com/user-attachments/assets/b3d6de5a-9807-4cd0-adb4-76a877c283f5)

### User Profile
![User Profile](https://github.com/user-attachments/assets/208daf01-2a33-43c9-80ea-29ba2de5046b)


### Blog Management
![Blog Management Screenshot](https://github.com/user-attachments/assets/71a12767-ad7a-4a90-bc30-c3dfc178da2d)


---

## 🤝 Contributing

Contributions are welcome! If you'd like to contribute, please:
1. Fork the repository.
2. Create a feature branch:
   ```bash
   git checkout -b feature/your-feature
   ```
3. Commit your changes:
   ```bash
   git commit -m "Add your feature"
   ```
4. Push to your branch:
   ```bash
   git push origin feature/your-feature
   ```
5. Create a pull request.

---

## ✨ Acknowledgments

- Thanks to **Spring Boot** for making backend development smooth and efficient.
- Inspired by modern blogging platforms to provide an elegant user experience.

---
