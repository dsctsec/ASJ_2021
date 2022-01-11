# Remindi - Lecture Reminder

## App Images
<table>
  <tr>
    <td><img src="https://user-images.githubusercontent.com/50910066/148781499-a54e28c5-ffa0-40a0-b2b8-23c4e61effee.jpeg" alt="Home Screen"></td>
    <td><img src="https://user-images.githubusercontent.com/50910066/148782774-ff3a23d9-c78c-43ca-901d-e03189ee6b8b.jpeg" alt="Weekday screen"></td>
    <td><img src="https://user-images.githubusercontent.com/50910066/148785786-7ad96ebf-8e4a-45e8-8a38-6afb730ea5c7.jpeg" alt="Edit screen"></td>
  </tr>
  <tr>
    <td><img src="https://user-images.githubusercontent.com/50910066/148786547-bdf5bf65-d0e9-4f95-9222-feb5685db7e6.jpeg" alt="Empty lecture list"></td>
    <td><img src="https://user-images.githubusercontent.com/50910066/148786890-1a8b226f-b926-4e91-94e0-10a02a449733.jpeg" alt="Lecture list"></td>
  </tr>
</table>

## Problem Statement

Being in college one thing we all can agree on is that we miss our lectures. The idea is to create an App to keep us up-to date with upcomming lectures with an amazing UI experience.

## Proposed Solution

A simple yet elegant solution is to sent reminders on phone by helping us to plan our future steps.
"Remindi" shows user with the day's lectures and also the week's schedule in thier hand.
    	  	
## Functionality & Concepts used

The App has a very beautiful and a simple interactive interface which helps us to easily navigate and plan for us. Following are few android concepts used to achieve the functionalities in app: 
- **Constraint Layout:** Most of the activities in the app uses a flexible constraint layout, which is easy to handle for different screen sizes.
- **Simple & Easy Views Design:** It was a text heavy applications with a few other elements like Progress indicator and others to build views.
- **RecyclerView:** To present the list of different route busses we used the efficient recyclerview.
- **LiveData & Room Database:** We are also using LiveData to update & observe any changes in the Lecture Database. So if any changes are made in our database it will be reflected into our UI.
- **Navigation:** Jetpack's Navigation component is used for easier navigation between fragments and passing easy data with SafeArgs to the next fragments.

## Application Link & Future Scope

The app is currently in the Alpha testing phase. 

- You can access the app: [0.0.1](https://github.com/dsctsec/ASJ_2021/releases/tag/0.0.1).

The app can include attendance management module and a central server to store lecture time and details.
