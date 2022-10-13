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


When opening Application first, `LoginActivity.java` runs. Users must sign in with authentic provided by admin. After logining sucessful, the main of this application for using starts. It have five Client : Home, Calendar, Task, Account Settings, AddTask. Each Client can access by menu-bar in bottom. The menu-bar has five buttons for five Client above. If Users click on button of Home client, file called `MainActivity.java` will run. Else if Users click on buttons of Task, Account Settings, The files `TaskList.java`, `AccountSettingsActivity.java` will start. AddTask Client has not menubar instead of a back arrow to return the Home Client or a button-save to go to Task client after creating task sucessful. User can sign out your authentic with button-logout in Account Settings.       

</details>


<details>
<summary> 4.2. Login</summary>

 This file called `Login.java` link to `activity_login.xml` for front-end. It also link to `MainActivity.java ` (Home Client) in order to access when user logins successful and `RegisterActivity.java` to go to Register Client. Authentic is created by admin, stored in firebase Authentic, if you do not have account, contact admin to sign up (Admin: **Son Tran BK**). The purpose of this file is to sign in account for using our application.


![image](https://user-images.githubusercontent.com/81752065/195138977-8bfa19b5-340a-4844-b497-b903550fd63d.png)

</details>


<details>
<summary> 4.3. Register</summary>

The file called `Register.java` links to `activity_register.xml` for front-end.It also links to `MainActivity.java ` (Home Client) in order to access when user register successful. The purose of this file is to create account but only admin can create authentic. When admin creates a account sucessful, data of account is stored on firebase.
Account of this application follows the format:
- Name: First Name + Last Name
- Email : name@task.com
- Password default : ******  

![image](https://user-images.githubusercontent.com/81752065/195139403-7cdd94e1-db1f-419d-9ee6-da7e61f002d6.png)

![image](https://user-images.githubusercontent.com/81752065/195139565-9f4f2b16-a58f-4157-9254-95dc891f0b59.png)

</details>



<details>
<summary> 4.4. Home Client</summary>

The file called `MainActivity.java` links to `activity_main.xml` to front end. It also links to `AddModifyTask_Company.java`, `TaskList.java`, `AccountSettingsActivity.java` for transfering each client. In Home Client has a menu-bar which is mentioned in section **4.1. Overview** and a toolbar include :   
 - **_Name of User_** get data from firebase Realtime in reference("User")
 - Toolbar in format : "Hello! **_Name of User_**"
![image](https://user-images.githubusercontent.com/81752065/195139918-7b8a4065-5550-43df-95a3-b4aab098b55f.png)

</details>


<details>
<summary> 4.5. Task List</summary>

This file called `TaskList.java` and is connected to `task_list_row_company.xml` for display list of task; `AddModifyTask_Company.java`, `AccountSettingsActivity.java`, `MainActivity.java` for transfering each client . It also link to `MyAdapter_Company.java` in order that each task created will display in a block with element : NameTask, status of task("In process", "Cancel", "Complete"), member who is assigned this task by leader. Task is ranged according to creation time meaning task created earlier is in front of task later. If user click on any Task, Client "Update Task" start; click on CheckBox, Task which CheckBox is clicked changes status to "Complete"; remove tick CheckBox, Status of Task change to "In process".

![image](https://user-images.githubusercontent.com/81752065/195140416-ddc6b046-1e43-4a10-b449-5cf8b542514e.png)
</details>



<details>
<summary> 4.6. Add Task</summary>

Task Client include fields :
- NameTask
- Description of Task
- time-begin task
- time-finish task
- Members assigned by leader must do task


The file called `AddModifyTask_Company.java` links to `activity_add_modify_task_company.xml` for display element of Task. It also links to `userList.java` for leader assign  selected users this task. The purpose of this file is for leader assign members in group a task. When `userList.java` runs, a client with all users in group gotten data from firebase appears. If tick on a user, notification pop up "user is add to task". Finally, click on Complete to back "Add Task" Client, save Member and back to **Add Task** client. With time-end, it is longer than time-finsh. Leader will fill all field and click on button "Save", Data of Task(NameTask,Description,Timebegin,Timeend,Member) put to firebase realtime in reference("Task") with id for Task.


![image](https://user-images.githubusercontent.com/81752065/195140867-8037dad6-5a8b-4722-98b0-a877aa689465.png)

</details>



<details>
<summary> 4.7. Update Task</summary>
Client "Update Task" include :
- NameTask (data get from firebase realtime )
- Description of Task(data get from firebase realtime)
- Time begin task(data from firebase realtime)
- Time finish task(data get from firebase realtime)
- Members(data get from firebase realtime)
- Status of Task
- Button Update 
- Button Delete

The file called `Update_Delete_Task_Company.java` links to`activity_delete_update_task_company.xml` for front-end. Leader can update task by refill all fields (NameTask, Description of Task, time begin, time end, members, status of task).If Leader click on field member, the file called `userList_Update.java` runs. A client having all members in group open. Leader select people and click on button-complete to update member for task. When leader click on field **Status of Task**, Client links file called `SetStatus.java` open. Leader can set status of task. If task is completed, set status "Complete"; task is canceled, set "Cancel"; task is in process, set "In process". After setting status, click button-Set to stored and back to Update Client.
If click button "Update", Data of Task which edited is updated on firebase also task list; click button "Delete", Task and data of this task on firebase is removed.

![image](https://user-images.githubusercontent.com/81752065/195142494-e950e71b-625d-4182-bc7e-3374cea3dc56.png)

</details>

<details>
<summary> 4.8. Settings Account</summary>

The file called `AccountSettingsActivity.java` connected to `activity_profile.xml`. This client has a picture about user,Name user, email of user which get from realtiem database on firebase. Futhermore, it also has three button(Edit profile, change password, Logout).If you click on button-logout, authentic sign out and go to Login client.

![image](https://user-images.githubusercontent.com/81752065/195036044-80e7ccc3-bbef-4399-8db1-c62700ffa9a1.png)

</details>

## 5. Authors and credits:
In research and development process, all credits go to ***Son Tran BK (1st author) and CTARG LAB members***, in EEE, HUST (Hanoi University of Science and Technology), Ha Noi, Viet Nam