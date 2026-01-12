// 表单提交处理
document.getElementById('taskForm').addEventListener('submit', function(e) {
    e.preventDefault();
    
    const title = document.getElementById('title').value;
    const description = document.getElementById('description').value;
    const deadline = document.getElementById('deadline').value;

    const table = document.getElementById('taskTable').getElementsByTagName('tbody')[0];
    const newRow = table.insertRow();
    
    newRow.insertCell(0).textContent = title;
    newRow.insertCell(1).textContent = description;
    newRow.insertCell(2).textContent = deadline;
    newRow.insertCell(3).textContent = '待处理';

    // 清空表单
    this.reset();
});

// 导航栏响应式切换
document.addEventListener('DOMContentLoaded', function() {
    const navLinks = document.querySelector('.nav-links');
    const menuToggle = document.createElement('button');
    menuToggle.textContent = '☰';
    menuToggle.className = 'menu-toggle';
    menuToggle.onclick = function() {
        navLinks.classList.toggle('active');
    };
    
    document.querySelector('.navbar').appendChild(menuToggle);
});