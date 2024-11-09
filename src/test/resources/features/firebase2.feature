Feature:implementing login functionality 

@smoke
Scenario: Succesfull login

Given the url "https://qa-tekarch.firebaseapp.com/"
When i land in "LoginPage"
When i enter the username as "admin123@gmail.com"
And i enter the password as "admin123"
When i click the login button
When i land in "HomePage"
Then i should be able to see homepage

@smoke @regression
Scenario: unSuccesfull login

Given the url "https://qa-tekarch.firebaseapp.com/"
When i land in "LoginPage"
When i enter the username as "admin123@gmail.com"
And i enter the password as "xyz"
When i click the login button
Then i should be able to see error message "Error : The password is invalid or the user does not have a password."

