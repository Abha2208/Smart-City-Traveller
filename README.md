# 🌍 Smart City Traveller

## 📌 Project Description

Smart City Traveller is a Java-based desktop application that helps users explore cities efficiently by providing intelligent recommendations for places based on category, rating, and budget.

The system allows users to navigate through states and cities, select categories, and discover relevant places such as tourist attractions, food spots, shopping areas, and adventure activities.

---

## 🚀 Features

* 🔐 User authentication (Login & Signup)
* 🗺️ State → City dynamic selection
* 📂 Category-based exploration (Tourist, Food, Shopping, Fun)
* 🤖 KNN-based recommendation system (based on rating & price)
* 🔄 Sorting options:

  * Recommended (KNN)
  * Price (Low to High)
  * Rating (High to Low)
* 💾 MySQL database integration using JDBC
* 🎨 Interactive UI built with JavaFX

---

## 🧠 Recommendation Logic

The system uses a **K-Nearest Neighbors (KNN)** inspired approach:

* Considers **rating** and **price**
* Applies **Euclidean distance**
* Recommends places closest to an ideal combination of:

  * ⭐ High rating
  * 💰 Affordable price

---

## 🛠️ Technologies Used

* Java
* JavaFX
* MySQL
* JDBC
* Data Structures & Algorithms (KNN concept)

---

## ▶️ How to Run

1. Open the project in IntelliJ IDEA
2. Configure MySQL database (`smart_city`)
3. Update database credentials in code if needed
4. Run `LoginPage.java`
5. Select state → city → category → explore recommendations

---

## 📂 Project Structure

* `LoginPage.java` → User authentication
* `CityPage.java` → State & city selection
* `CategoryPage.java` → Category selection
* `PlacesPage.java` → Recommendation logic & display

---

## 🔮 Future Enhancements

* ❤️ Personalized recommendations based on user history
* 🎯 Budget filter slider
* 🌐 API integration for real-time data
* 📱 Web or mobile version

---

## 👩‍💻 Author

**Abha Gupta**
