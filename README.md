# Recipes App - Native Android App (Kotlin)

This project is a Native Android Application built using Kotlin. The app is designed to offer a seamless and user-friendly experience, delivering a wide range of features for food lovers to browse, save, and access recipes. 

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Setup and Installation](#setup-and-installation)
- [Screenshots](#screenshots)
- [Architecture](#architecture)
- [Contributing](#contributing)
- [License](#license)

## Overview
The **Recipes App** leverages modern Android development practices, including API integration, local storage, and smooth navigation between various app sections. Users can browse a rich collection of recipes fetched via TheMealDB API, save their favorites for offline access, and enjoy a personalized experience through shared preferences. 

This project was developed in collaboration with the **Information Technology Institute (ITI)**.

## Features
- **Retrofit API Integration**: Uses TheMealDB API to fetch and display a wide range of recipes.
- **Room Database**: Allows users to save and manage favorite recipes for offline access.
- **Dark Mode and Smooth Animations**: Offers a modern, aesthetically pleasing UI with smooth animations and dark mode support.
- **Advanced Fragment Navigation**: Implements intuitive navigation between multiple screens, including:
  - Splash Screen
  - Login
  - Registration
  - Home
  - Favorites
  - Search
  - Recipe Detail
- **YouTube-like Video Playback**: Supports immersive video playback for recipe instructions.
- **Secure Authentication**: Includes secure login and registration with credentials stored locally using Room database.
- **Personalization with Shared Preferences**: Tracks user preferences and login status for a tailored experience.
- **MVVM Architecture**: Follows the Model-View-ViewModel pattern to keep the codebase maintainable and scalable.

## Technologies Used
- **Kotlin**: The programming language used to build the app.
- **Retrofit**: For API integration to fetch recipes from TheMealDB.
- **Room Database**: Local storage solution for offline access to favorite recipes.
- **Navigation Component**: For fragment navigation and handling transitions.
- **ViewModel & LiveData**: Part of the MVVM architecture for managing UI-related data.
- **Coroutines**: Used for asynchronous operations.
- **Glide/Picasso**: Image loading library for loading recipe images.
- **ExoPlayer/YouTube API**: For handling video playback.

## Setup and Installation
To get started with the project:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/Ebrahim011/RecipeApp.git
![WhatsApp Image 2024-09-17 at 15 37 31_80f9a20a](https://github.com/user-attachments/assets/d9cc979f-630b-4389-b647-14c52fe1d099)
