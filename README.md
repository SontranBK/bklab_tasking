# ***BK LAB Tasking*** - an app for group meeting and tasks asssigning 

## 1. Getting Started
An app for small and medium organizations (SME), focus on group meeting and tasks asssigning. Main features:
- Create personal tasks for invidiuals
- Create group tasks, leader can assign tasks to members
- Support deadline reminders, tasks' status (to do, in progress, done)
- Authiencation log-in and sign-in
## 2. Updates and releases!!!
For all release features and publised product UI, check out [release page](https://github.com/SontranBK/bklab_tasking/releases)
* 【Sep 16, 2022】 

## 3. Reproducing process
<details>
<summary> 3.1. Support plaftforms</summary>

- We support Android app only
- Tested on Redmi 9A and other Android devices.
</details>

<details>
<summary> 3.2. Reproduce steps on Android:</summary>

- Reproduce steps on physical devices: 
    1. Enable Developer Options on Android device setting (USB debug, install via USB). 
    2. Install Android Studio and plug your device into your computer.
    3. Open Android Studio and run app with Android Studio.
    4. If error return, run with following commands: flutter run --no-sound-null-safety
- Reproduce steps on virtual devices:
    1. Install Android Studio, create a virtual device on Android Studio
    2. Run app with Android Studio on virtual device
</details>


## 4. Developer guide

<details>
<summary> 4.1. Overview</summary>

This product is written in Java, the application include Clients:
- Login
- Register
- Home (In process of development)
- Calendar (In process of development)
- Task
- Account Settings
- Add Task 
- Update Task


![image](https://user-images.githubusercontent.com/81752065/195141713-8cefbd88-cc31-490b-989b-912886e0d06a.png)

</details>


<details>
<summary> 4.2. Login</summary>

### 4.2.1. File link
 - `Login.java` file link to `activity_login.xml`
### 4.2.2. Description
 Authentic is created by admin, stored in firebase Authentic, if you do not have account, contact admin to sign up (Admin: **Son Tran BK**). The purpose of this file is to sign in account for using our application
### 4.2.3. Diagram
![image](https://user-images.githubusercontent.com/81752065/195138977-8bfa19b5-340a-4844-b497-b903550fd63d.png)

</details>


<details>
<summary> 4.3. Register</summary>

### 4.3.1 File link
- `Register.java` file link to `activity_register.xml`
### 4.3.2. Description
- Purpose: Create account
### 4.3.3. Rules and Function
Account of this application follow the format:
- Name: First Name + Last Name
- Email : name@task.com
- Password default : ******  
### 4.3.4 Diagram
![image](https://user-images.githubusercontent.com/81752065/195139403-7cdd94e1-db1f-419d-9ee6-da7e61f002d6.png)

![image](https://user-images.githubusercontent.com/81752065/195139565-9f4f2b16-a58f-4157-9254-95dc891f0b59.png)

</details>



<details>
<summary> 4.4. Home Client</summary>

### 4.4.1. File Link
- `MainActivity.java` file link to `activity_main.xml`
### 4.4.2 Description
 - Name of User get data from firebase Realtime in reference("User")
 - Toolbar in format : "Hello! Name of User"
### 4.4.3. Diagram
![image](https://user-images.githubusercontent.com/81752065/195139918-7b8a4065-5550-43df-95a3-b4aab098b55f.png)

</details>


<details>
<summary> 4.5. Task List</summary>

### 4.5.1. File link
This file called `TaskList.java` and is connected to `task_list_row_company.xml`. It also link to ....
### 4.5.2. Description 
- Task is ranged according to the List, Each Task presents NameTask, Status of Task and Member assigned by leader 
- If click on a Task, Client "Update Task" start 
- If click on CheckBox, Task which CheckBox is clicked changes status to "Complete" 
- If remove tick CheckBox, Status of Task change to "In process"
### 4.5.3. Diagram
![image](https://user-images.githubusercontent.com/81752065/195140416-ddc6b046-1e43-4a10-b449-5cf8b542514e.png)
</details>



<details>
<summary> 4.6. Add Task</summary>

### 4.6.1. File link
- `AddModifyTask_Company.java` link to `activity_add_modify_task_company.xml`
### 4.6.2. Description
Task Client include :
- NameTask
- Description of Task
- Time begin task
- Time finish task
- Members assigned by leader must do task

### 4.6.3. Rules and Function
- Time finsh is longer than time begin
- Member get from data firebase reference("User")
- If tick on a user, notification pop up "user is add to task".Finally, click on Complete to back "Add Task" Client and to save Member
- Click on button "Save", Data of Task(NameTask,Description,Timebegin,Timeend,Member) put to firebase realtime in reference("Task") with id for Task 
### 4.6.4. Diagram
![image](https://user-images.githubusercontent.com/81752065/195140867-8037dad6-5a8b-4722-98b0-a877aa689465.png)

</details>



<details>
<summary> 4.7. Update Task</summary>


### 1.7.1. File Link
- `Update_Delete_Task_Company.java` file link to`activity_delete_update_task_company.xml`
### 1.7.2. Description
Client "Update Task" include :
- NameTask (data get from firebase realtime )
- Description of Task(data get from firebase realtime)
- Time begin task(data from firebase realtime)
- Time finish task(data get from firebase realtime)
- Members(data get from firebase realtime)
- Button Update 
- Button Delete
### 1.7.3. Fuction:
-If click button "Update", Data of Task which edited is Update on firebase
-If click button "Delete", Task is removed
### 1.7.4. Diagram
![image](https://user-images.githubusercontent.com/81752065/195142494-e950e71b-625d-4182-bc7e-3374cea3dc56.png)

</details>

<details>
<summary> 4.8. Settings Account</summary>

### 1.8.1. File link
- `AccountSettingsActivity.java` link to `activity_profile.xml`
### 1.8.2. Description
### 1.8.3. Rules and Function
### 1.8.4 Diagram
![image](https://user-images.githubusercontent.com/81752065/195036044-80e7ccc3-bbef-4399-8db1-c62700ffa9a1.png)

</details>

## 5. Authors and credits:
In research and development process, all credits go to ***Son Tran BK (1st author) and CTARG LAB members***, in EEE, HUST (Hanoi University of Science and Technology), Ha Noi, Viet Nam
