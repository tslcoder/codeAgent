script.js
// 初始化任务数据
let tasks = JSON.parse(localStorage.getItem('tasks')) || [];
let currentFilter = 'all';

// 获取DOM元素
const taskForm = document.getElementById('task-form');
const taskInput = document.getElementById('task-input');
const taskList = document.getElementById('task-list');
const filterButtons = document.querySelectorAll('.filter');

// 保存任务到localStorage
function saveTasks() {
    localStorage.setItem('tasks', JSON.stringify(tasks));
}

// 渲染任务列表
function renderTasks() {
    taskList.innerHTML = '';
    
    const filteredTasks = tasks.filter(task => {
        if (currentFilter === 'all') return true;
        return currentFilter === 'completed' ? task.completed : !task.completed;
    });

    filteredTasks.forEach(task => {
        const li = document.createElement('li');
        li.className = 'task-item';
        if (task.completed) li.classList.add('completed');

        li.innerHTML = `
            <input type="checkbox" class="task-check" ${task.completed ? 'checked' : ''} data-id="${task.id}">
            <span class="task-text">${task.text}</span>
            <div class="task-actions">
                <button class="btn btn-edit" data-id="${task.id}">编辑</button>
                <button class="btn btn-delete" data-id="${task.id}">删除</button>
            </div>
        `;
        
        taskList.appendChild(li);
    });
}

// 添加新任务
function addTask(text) {
    if (!text.trim()) return;
    
    const newTask = {
        id: Date.now(),
        text,
        completed: false
    };
    
    tasks.push(newTask);
    saveTasks();
    renderTasks();
}

// 切换任务状态
function toggleTask(id) {
    tasks = tasks.map(task => 
        task.id === id ? {...task, completed: !task.completed} : task
    );
    saveTasks();
    renderTasks();
}

// 删除任务
function deleteTask(id) {
    tasks = tasks.filter(task => task.id !== id);
    saveTasks();
    renderTasks();
}

// 编辑任务
function editTask(id, newText) {
    if (!newText.trim()) return;
    
    tasks = tasks.map(task => 
        task.id === id ? {...task, text: newText} : task
    );
    saveTasks();
    renderTasks();
}

// 设置过滤器
function setFilter(filter) {
    currentFilter = filter;
    document.querySelectorAll('.filter').forEach(btn => 
        btn.classList.toggle('active', btn.dataset.filter === filter)
    );
    renderTasks();
}

// 事件处理
taskForm.addEventListener('submit', (e) => {
    e.preventDefault();
    addTask(taskInput.value);
    taskInput.value = '';
});

taskList.addEventListener('click', (e) => {
    const id = parseInt(e.target.dataset.id);
    
    if (e.target.classList.contains('task-check')) {
        toggleTask(id);
    }
    
    if (e.target.classList.contains('btn-delete')) {
        deleteTask(id);
    }
    
    if (e.target.classList.contains('btn-edit')) {
        const newText = prompt('编辑任务:', tasks.find(t => t.id === id).text);
        if (newText !== null) editTask(id, newText);
    }
});

filterButtons.forEach(btn => 
    btn.addEventListener('click', () => setFilter(btn.dataset.filter))
);

// 初始化渲染
document.addEventListener('DOMContentLoaded', () => {
    renderTasks();
});