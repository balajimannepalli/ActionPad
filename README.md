A Simple Task Manager App created in Android Studio (Java)

Working:-
- New users can register and then login to the app
- The tasks along with their decription and date have to be added
- These tasks will be sorted based on date and displayed as a list view
- After completion, the task can be marked completed and then it will show the next task
  
Structure:-

 1] Classes:
  - MainActivity.java : Handles main screen(Registration and Login)
  - TasksActivity.java : Creation of tasks
  - TasksListActivity.java : Displays list of tasks
  - Task.java : Model class
  - TaskAdapter.java : Adapts task data to display in a list view
    
 2] Layout Resource files:
  - activity_main.xml : Main screen layout
  - activity_tasks.xml : Layout for creating tasks
  - activity_taskslist.xml : Layout for displaying tasks
  - task_item.xml : Layout for individual task items in the list
