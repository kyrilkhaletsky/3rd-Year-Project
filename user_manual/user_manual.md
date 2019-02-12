# ShoppingPal User Manual
![](code/ShoppingPal/app/src/main/res/mipmap-xxhdpi/ic_launcher.png)

## CA326

Ben Kelly & Kyrylo Khaletskyy

## Table of Contents
- [1. Introduction to ShoppingPal](#1-introduction)

- [2. Getting Started](#2-getting-started)
  * [2.1 Download](#21-download)
  * [2.2 Registration](#22-registration)
  * [2.3 Login](#23-login)
  * [2.4 Profile Selection](#24-profile-selection)
  * [2.5 Profile Creation](#25-profile-creation)
  
- [3. Scanning a barcode](#3-scanning-a-barcode)
  
- [4. Reading product data](#4-reading-product-data)

- [5. Settings Button](#5-settings-button)

- [6. Logging out](#6-logging-out)

- [7. Deleting an account](#7-deleting-an-account)



##  1 Introduction

Welcome to ShoppingPal, an Android application which helps users with dietary restrictions to buy groceries safely from a local store. This app is about to make
your life that tiny bit easier. Whether you have, allergies or intolerances to certain ingredients, pharmaceutical products or toiletries this is the app for you.
ShoppingPal also aims to help athletes with strict dietary and pharmaceutical restrictions buy items in a store without the worry of failing a drug test.
To download ShoppingPal and register your account now we recommend you read [Chapter 2](#2-getting-started).

Users will register with an email and password and proceed to set up their profile. A user's profile contains his/her restrictions at a given time. 
The app will provide a user with the 14 major food allergens, as well as a WADA (World Anti Doping Agency) specified banned substances list taken directly
from WADA themselves. Users who have specific allergies or restrictions not found in the common list provided have the option to set up their own profiles containing
ingredients manually selected/inputted by the user. Upon successful registry and profile set up, the user will be given the option to proceed to scan a barcode.
Once the barcode is scanned it will fetch that particular product in Tesco's database and return whether the user can consume this product based on their profile(s) selected.
The user will also be presented with the ingredients contained in sed product.

ShoppingPal is mostly aimed at people with allergies, intolerances and general restrictions but can be used by any user who wishes to avoid certain ingredients from their 
diet/use as well as people who have trouble reading labels in a store. It is estimated by the WAO (World Allergy Organisation) that allergy prevalence of the whole population 
by country ranges between 10-40% which in Ireland would total to at least 500,000 people. It is difficult to estimate the number of professional athletes in the world but 
according to WADA, there have been 303,369 samples of drugs tests taken in 2015. ShoppingPal can, therefore, be used by a very large group of people.

We are sure you will find ShoppingPal intuitive and easy to use. This user manual explains all you need to know about your ShoppingPal experience and how to get started. 



##  2 Getting Started

### 2.1 Download

*  Open Gitlab and open the following directory: https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/tree/master/code

*  Here you will find an APK file called "ShoppingPal"

*  Download it and save it to your Android Device

*  Allow the installation of Apps from Unknown Sources found in Android Settings

*  Click the APK file downloaded previously and press "Install"

*  Click on the app to open

### 2.2 Registration

In order for you to use this application, you must download and install ShoppingPal. Upon successful installation, you will then be asked to enter an email (which will 
be used as a username) and password. Ensure that you enter a valid email address and that your password is at least 6 characters long. If you entered an invalid email or a
password that is too weak the app will notify you through a Toast message. Once you have completed this step the app will log you in, if you are an existing user you should
[2.3 Login](#23-login).

<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/f5769af3ccbfcebc67c864f649277f129a4478d4/testing/media/screenshots/screenshot%20(15).png" width="213.8" height="380" />
<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/f5769af3ccbfcebc67c864f649277f129a4478d4/testing/media/screenshots/screenshot%20(14).png" width="213.8" height="380" />
<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/f5769af3ccbfcebc67c864f649277f129a4478d4/testing/media/screenshots/screenshot%20(27).png" width="213.8" height="380" />
<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/f5769af3ccbfcebc67c864f649277f129a4478d4/testing/media/screenshots/screenshot%20(11).png" width="213.8" height="380" />

### 2.3 Login

You should login in with an email and password that you created earlier, once a user is logged into the app he/she does not need to log in again until they have
logged out or changed/reset their device. If your details are incorrect or you haven't created an account yet you will be prompted to create one. 
See [Registration](#22-registration) for details.

<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/f5769af3ccbfcebc67c864f649277f129a4478d4/testing/media/screenshots/screenshot%20(12).png" width="213.8" height="380" />
<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/f5769af3ccbfcebc67c864f649277f129a4478d4/testing/media/screenshots/screenshot%20(25).png" width="213.8" height="380" />
<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/f5769af3ccbfcebc67c864f649277f129a4478d4/testing/media/screenshots/screenshot%20(28).png" width="213.8" height="380" />
<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/f5769af3ccbfcebc67c864f649277f129a4478d4/testing/media/screenshots/screenshot%20(16).png" width="213.8" height="380" />

### 2.4 Profile Selection

Upon initial entry into the app, you will be provided with a list of the 14 major food allergens, as well as WADA banned substances list. These set of profiles
will be a great starting point for most users. Once you have selected the profile you intend on using, you can continue to scan a barcode of an item with that
particular profile information selected. You should choose the profile which you are allergic/intolerant to. After selecting a profile you can view its contents by pressing the
"Show Selected Items" button. If you do not see the specific ingredient you want to avoid you should read [Profile Creation](#25-profile-creation).

<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/f5769af3ccbfcebc67c864f649277f129a4478d4/testing/media/screenshots/screenshot%20(29).png" width="213.8" height="380" />
<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/f5769af3ccbfcebc67c864f649277f129a4478d4/testing/media/screenshots/screenshot%20(4).png" width="213.8" height="380" />

### 2.5 Profile Creation

Any user who doesn't find a profile which they need can create their own profile. To do this you will need to press the "Create Profile" button. You will then
be presented with two text bars (Profile Name and Ingredient). You should enter the profile name and then enter each ingredient which you wish to avoid __individually__.
After entering an ingredient you then need to press the "Add Item" button. If added correctly you will see the list in the middle of your screen. You will see the
list grow with each ingredient that you add to the profile. You should be extremely careful to __spell-check everything__ because if something is misspelled it will
be useless when checking if you can take a product, for this you can use Android's inbuilt spell checker. After you have finished adding ingredients to your profile you 
need to press the "Save Profile" button to save. You will then see the notification which states that your profile has been added successfully. To get back to the home screen 
you will need to press the back button. After successful creation, you will be brought back to the homepage where you can now see a new profile that you've created. It 
will be among the list of profiles which are already contained within the drop-down menu

<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/f5769af3ccbfcebc67c864f649277f129a4478d4/testing/media/screenshots/screenshot%20(5).png" width="213.8" height="380" />
<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/f5769af3ccbfcebc67c864f649277f129a4478d4/testing/media/screenshots/screenshot%20(6).png" width="213.8" height="380" />
<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/f5769af3ccbfcebc67c864f649277f129a4478d4/testing/media/screenshots/screenshot%20(7).png" width="213.8" height="380" />
<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/f5769af3ccbfcebc67c864f649277f129a4478d4/testing/media/screenshots/screenshot%20(8).png" width="213.8" height="380" />

## 3 Scanning a barcode

To retrieve product data, the rear camera is used to read the barcode of an item.
Upon selecting a profile to use you should press the scan button. If a profile has not been selected the system will prompt the user to select one. The app will then
ask for permission to use your rear camera to scan barcodes, rather than you granting it manually in Android Settings.

<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/f5769af3ccbfcebc67c864f649277f129a4478d4/testing/media/screenshots/screenshot%20(9).png" width="213.8" height="380" />

Once you grant permission you won't be required to regrant these permissions every time you use the scan function.
When the "Scan" button is pressed the user will be presented with the following screen:

<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/f5769af3ccbfcebc67c864f649277f129a4478d4/testing/media/screenshots/screenshot%20(19).png" width="213.8" height="380" />

The app will open the rear camera of the mobile device and present the user with a horizontal line across the screen where the user should aim to point the barcode at.
The user should place the line perpendicularly to the lines of the barcode. When a user successfully scans an item's barcode he/she will be presented with the product data
and information page. For instruction on how to read this page see [Reading product data](#4-reading-product-data).

## 4 Reading product data

After a user successfully scans an item, he/she is presented with the product information such as product name and product barcode and (list of ingredients found in the item 
shown on the left) as well as information on whether it is safe for the user to consume the product. The ingredients in the item you scanned will be compared to the ingredients 
contained in the profile you have chosen. The ingredients that match the ingredients found and ingredients in your profile will be shown on the right hand side. A user can 
scroll through both lists.
*  If the product is safe to consume the screen will turn green and display a message saying "Product is Safe for Consumption".
*  The screen will look like the image below:

<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/f5769af3ccbfcebc67c864f649277f129a4478d4/testing/media/screenshots/screenshot%20(21).png" width="213.8" height="380" />

*  If the product is not safe for you to consume the screen will turn red and display a message saying "Product Not Safe for Consumption"
*  The screen will look like the image below:

<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/f5769af3ccbfcebc67c864f649277f129a4478d4/testing/media/screenshots/screenshot%20(20).png" width="213.8" height="380" />

*  If the product cannot be found on the database the screen will remain blue and display a message saying "No Data Available for this Product"
*  The screen will look like the image below:

<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/f5769af3ccbfcebc67c864f649277f129a4478d4/testing/media/screenshots/screenshot%20(22).png" width="213.8" height="380" />

You will then need to press the back button on your device to go back to the homescreen where you can scan another item.

## 5 Settings Button

By pressing the settings button on the top right corner of your screen you will be presented with the following menu

<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/f5769af3ccbfcebc67c864f649277f129a4478d4/testing/media/screenshots/screenshot%20(2).png" width="213.8" height="380" />
 
Here you can choose to [Log out](#6-logging-out) or [Delete your account](#7-deleting-an-account)

## 6 Logging out
To log out you will need to:
*  Press the settings button in the top right corner of your screen.
*  Press the "logout" button

## 7 Deleting an account
We recognize that a user's needs may change in the future, therefore every user has the option to delete their account.
If a user decides to Delete their account he/she can do so by entering the account settings screen. This will be accessible from the homescreen provided the user has an
existing account and is logged in.

To delete your account you will need to:
*  Press the settings button in the top right corner of your screen.
*  Press the red "Delete Account" button
*  Press "Delete" when asked are you sure you would like to delete your account

<img src="https://gitlab.computing.dcu.ie/kellyb45/2018-CA326-kellyb45-shoppingpal/raw/f5769af3ccbfcebc67c864f649277f129a4478d4/testing/media/screenshots/screenshot%20(3).png" width="213.8" height="380" />

