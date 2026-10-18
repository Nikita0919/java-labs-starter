package edu.course.lab02;

public class ProjectTask {
    private final String id;
    private final String title;
    private TaskStatus status;
    private int estimatedHours;

    public ProjectTask(String id, String title, int estimatedHours){
        if (id == null || id.isBlank()){
            throw new IllegalArgumentException("id не должен быть пустым");
        }
        if (title == null || title.isBlank()){
            throw new IllegalArgumentException("title не должен быть пустым");
        }
        if (estimatedHours <= 0){
            throw new IllegalArgumentException("Трудоёмкость должна быть положительной");
        }
        this.id = id.trim();
        this.title = title.trim();
        this.estimatedHours = estimatedHours;
        this.status = TaskStatus.NEW;
    }

    public String getId(){return id;}
    public String getTitle(){return title;}
    public TaskStatus getStatus(){return status;}
    public int getEstimatedHours(){return estimatedHours;}

    public void changeStatus(TaskStatus newStatus){
        if (newStatus == null) {
            throw new IllegalArgumentException("Статус не может быть null");
        }
        this.status = newStatus;
    }

    public boolean isCompleted(){
        return status == TaskStatus.DONE;
    }

    public void increaseEstimate(int extraHours){
        if (extraHours <= 0){
            throw new IllegalArgumentException("Доп часы должны быть положительными");
        }
        this.estimatedHours += extraHours;
    }
}