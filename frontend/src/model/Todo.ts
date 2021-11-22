class Todo {

    id: string
    text: string
    completed: boolean
  
    constructor(id: string, value: string, completed: boolean) {
      this.id = id;
      this.text = value;
      this.completed = completed;
    }
}

export default Todo