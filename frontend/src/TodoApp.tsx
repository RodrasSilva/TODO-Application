import { useEffect, useState } from 'react';
import './TodoApp.css';
import Todo from "./model/Todo"
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

const { REACT_APP_HOST_IP_ADDRESS, REACT_APP_HOST_PORT } = process.env
const BASE_URL = "toDos"

// Create http client to connect to backend
const axios = require('axios').default;
const instance = axios.create({
  baseURL: `http://${REACT_APP_HOST_IP_ADDRESS}:${REACT_APP_HOST_PORT}`
});


function TodoFormComponent(props: { addTodo: (value: string) => void }) {
  const [value, setValue] = useState<string>("")

  async function onSubmit() {
    if (value === "") return
    props.addTodo(value)
    setValue("")
  }

  return (
    <div id="form">
      <div id="text-field">
        <TextField id="standard-basic" style={{ width: "35%" }} onChange={(evt) => setValue(evt.target.value as string)} value={value} label="TODO" variant="standard" />
      </div>
      <div id="create-button">
        <Button variant="outlined" onClick={onSubmit}>Add TODO</Button>
      </div>
    </div>
  )

}

function TodoListComponent(props: { todos: Todo[], onMark: (index: number, status: boolean) => void, onRemove: (index: number) => void }) {
  return (
    <TableContainer component={Paper}>
      <Table style={{ width: "100%" }} aria-label="simple table">
        <TableBody>
          {props.todos.map((todo, idx) => (
            <TableRow
              key={todo.id}
              sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
            >
              <TableCell component="th" scope="row">
                <div id="todoValue">
                  <span style={{ textDecoration: todo.completed ? "line-through" : "" }}>{todo.text}</span>
                </div>
                <div id="buttons">
                  {
                    !todo.completed
                      ? <Button variant="outlined" color="primary" onClick={() => props.onMark(idx, true)}> Not Completed </Button>
                      : <Button variant="outlined" color="success" onClick={() => props.onMark(idx, false)}> Completed </Button>

                  }
                  <Button variant="outlined" style={{ marginLeft: "10px" }} color="error" onClick={() => props.onRemove(idx)}> Delete </Button>
                </div>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  )
}

function App() {

  const [todos, setTodos] = useState<Todo[]>([])

  useEffect(() => {
    // Debugging purposes
    console.log(process.env)
    console.log(REACT_APP_HOST_IP_ADDRESS)
    console.log(REACT_APP_HOST_PORT)
    fetchTodos()
  }, [])

  async function fetchTodos() {
    const response = await instance.get(BASE_URL)
    setTodos(response.data)
  }

  async function addTodo(value: string) {
    const response = await instance.post(BASE_URL, { text: value })
    const newTodo = response.data
    const newTodos: Todo[] = todos.slice()
    newTodos.push(newTodo)
    setTodos(newTodos)
  }

  async function markTodo(index: number, status: boolean) {
    const todoToMark = todos[index]
    const newTodos = [...todos];
    await instance.patch(`${BASE_URL}/${todoToMark.id}`, {
      completed: status
    })
    newTodos[index].completed = status;
    setTodos(newTodos);
  };

  async function removeTodo(index: number) {
    const todoToDelete = todos[index]
    await instance.delete(`${BASE_URL}/${todoToDelete.id}`)
    const newTodos = [...todos];
    newTodos.splice(index, 1);
    setTodos(newTodos);
  };


  return (
    <div className="App">
      <TodoFormComponent addTodo={addTodo} />
      <TodoListComponent todos={todos} onMark={markTodo} onRemove={removeTodo} />
    </div>
  );
}

export default App;
