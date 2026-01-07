// script.js
document.addEventListener('DOMContentLoaded', () => {
    const taskForm = document.getElementById('task-form');
    const taskInput = document.getElementById('task-input');
    const taskList = document.getElementById('task-list');

    // 处理表单提交
    taskForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const taskText = taskInput.value.trim();
        
        if (taskText !== '') {
            createTaskItem(taskText);
            taskInput.value = '';
            taskInput.focus();
        }
    });

    // 创建任务项
    function createTaskItem(text) {
        const li = document.createElement('li');
        li.className = 'task-item';

        // 创建复选框容器
        const checkboxContainer = document.createElement('div');
        checkboxContainer.className = 'checkbox-container';

        const checkbox = document.createElement('input');
        checkbox.type = 'checkbox';
        checkbox.className = 'task-checkbox';

        // 创建任务文本
        const span = document.createElement('span');
        span.className = 'task-text';
        span.textContent = text;

        // 创建删除按钮
        const deleteBtn = document.createElement('button');
        deleteBtn.className = 'btn delete-btn';
        deleteBtn.textContent = '删除';

        // 组合元素
        checkboxContainer.appendChild(checkbox);
        checkboxContainer.appendChild(span);
        li.appendChild(checkboxContainer);
        li.appendChild(deleteBtn);

        // 添加事件监听器
        checkbox.addEventListener('change', () => {
            li.classList.toggle('completed');
        });

        deleteBtn.addEventListener('click', () => {
            taskList.removeChild(li);
        });

        taskList.appendChild(li);
    }
});