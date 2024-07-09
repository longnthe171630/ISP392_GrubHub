const allSideMenu = document.querySelectorAll('.side-menu a');
const logoutButton = document.getElementById('logoutButton');
document.addEventListener('DOMContentLoaded', (event) => {
    const currentPage = window.location.pathname;
    // Đặt trạng thái "active" dựa trên URL hiện tại
    allSideMenu.forEach(item => {
        const li = item.parentElement;
        li.classList.remove('active');
        if (currentPage.includes(item.getAttribute('href'))) {
            li.classList.add('active');
        }
    });
    // Đồng bộ hóa với localStorage nếu cần
    const activePage = localStorage.getItem('activePage');
    if (activePage && activePage !== currentPage) {
        allSideMenu.forEach(item => {
            const li = item.parentElement;
            li.classList.remove('active');
            if (currentPage.includes(item.getAttribute('href'))) {
                li.classList.add('active');
            }
        });
    }
});
// Lắng nghe sự kiện click
allSideMenu.forEach(item => {
    const li = item.parentElement;
    item.addEventListener('click', function (event) {
        // Lưu đường dẫn vào localStorage
        localStorage.setItem('activePage', item.getAttribute('href'));
        // Đặt trạng thái "active" trước khi điều hướng
        allSideMenu.forEach(i => {
            i.parentElement.classList.remove('active');
        });
        li.classList.add('active');
    });
});
// Lắng nghe sự kiện click cho nút logout
if (logoutButton) {
    logoutButton.addEventListener('click', function (event) {
// Xóa giá trị lưu trữ trong localStorage
        localStorage.removeItem('activePage');
    });
}


//Side bar
const menuBar = document.querySelector('#content nav .bx.bx-menu');
const sidebar = document.getElementById('sidebar');
// Kiểm tra trạng thái lưu trữ và thiết lập trạng thái sidebar khi tải trang
if (localStorage.getItem('sidebarState') === 'hide') {
    sidebar.classList.add('hide');
}

menuBar.addEventListener('click', function () {
    sidebar.classList.toggle('hide');
    // Lưu trạng thái sidebar vào localStorage
    if (sidebar.classList.contains('hide')) {
        localStorage.setItem('sidebarState', 'hide');
    } else {
        localStorage.removeItem('sidebarState');
    }
});
// Xử lý khi click ra ngoài màn hình
window.onclick = function (event) {
    if (!event.target.matches('.bx-filter') &&
            !event.target.matches('.bxs-bell') &&
            !event.target.matches('.avatar')) {
        let dropdowns = document.getElementsByClassName("dropdown-content");
        for (let i = 0; i < dropdowns.length; i++) {
            let openDropdown = dropdowns[i];
            if (openDropdown.classList.contains('show')) {
                openDropdown.classList.remove('show');
            }
        }
    }
};
//On/Off mode dark/night
const switchMode = document.getElementById('switch-mode');
switchMode.addEventListener('change', function () {
    if (this.checked) {
        document.body.classList.add('dark');
    } else {
        document.body.classList.remove('dark');
    }
});
var currentOpenDropdown = null;
// Function to toggle dropdown visibility
function toggleDropdown(id) {
    var dropdown = document.getElementById(id);
    // Close the currently open dropdown if it's not the same as the one being toggled
    if (currentOpenDropdown && currentOpenDropdown !== dropdown) {
        currentOpenDropdown.style.display = "none";
    }

    // Toggle the display of the dropdown
    if (dropdown.style.display === "block") {
        dropdown.style.display = "none";
        currentOpenDropdown = null;
    } else {
        dropdown.style.display = "block";
        currentOpenDropdown = dropdown;
    }
}

// Close the dropdown if the user clicks outside of it
window.addEventListener('click', function (event) {
// Check if the click was outside of the currently open dropdown and its trigger
    if (currentOpenDropdown && !event.target.closest('.dropdown-container')) {
        currentOpenDropdown.style.display = "none";
        currentOpenDropdown = null;
    }
});
// Đóng modal khi nhấp ra ngoài modal
window.onclick = function (event) {
    var modal = document.getElementById("myModal");
    if (event.target === modal) {
        modal.style.display = "none";
    }
};
// Mở modal nhập lí do khi nhấn nút "Thất bại"
function openReasonModal(id) {
    var modal = document.createElement('div');
    modal.classList.add('modal');
    var modalContent = document.createElement('div');
    modalContent.classList.add('reason-modal-content');
    var closeBtn = document.createElement('span');
    closeBtn.classList.add('close');
    closeBtn.innerHTML = '&times;';
    closeBtn.onclick = function () {
        modal.style.display = 'none';
    };
    //Input lí do
    var reasonInput = document.createElement('textarea');
    reasonInput.setAttribute('placeholder', 'What happend is it?...');
    reasonInput.style.width = '400px'; // Đặt chiều rộng textarea
    reasonInput.style.height = '125px'; // Đặt chiều cao textarea
    // Thiết lập kiểu chữ và kích thước
    reasonInput.style.fontFamily = 'Arial, sans-serif'; // Font chữ và fallback
    reasonInput.style.fontSize = '18px'; // Kích thước chữ
    reasonInput.style.padding = '5px'; // Thiết lập padding là 5px

    var fileInput = document.getElementById('fileInput');
    //Button xác nhận
    var saveBtn = document.createElement('button');
    saveBtn.innerText = 'Xác nhận';
    saveBtn.onclick = function () {
        var reason = reasonInput.value.trim();
        var file = fileInput.files[0];
        if (reason !== '' && file) {
            // Tạo form động
            var form = document.createElement('form');
            form.method = 'POST';
            form.action = 'deliverystatus';
            form.enctype = 'multipart/form-data'; // Đảm bảo form có thể gửi file

            // Tạo input hidden và đặt giá trị là 'failure'
            var actionInput = document.createElement('input');
            actionInput.type = 'hidden';
            actionInput.name = 'action';
            actionInput.value = 'failure';
            form.appendChild(actionInput);
            // Tạo input ẩn cho lý do
            var reasonField = document.createElement('input');
            reasonField.type = 'hidden';
            reasonField.name = 'reason';
            reasonField.value = reason;
            form.appendChild(reasonField);
            // Tạo input file để gửi lên server
            var fileField = document.createElement('input');
            fileField.type = 'file';
            fileField.name = 'photo'; // Tên field trong formData trên server
            fileField.files = fileInput.files; // Gán giá trị file đã chọn
            form.appendChild(fileField);
            //Tạo input ẩn cho id
            var orderIdInput = document.createElement('input');
            orderIdInput.type = 'hidden';
            orderIdInput.name = 'id';
            orderIdInput.value = id;
            form.appendChild(orderIdInput);
            // Append form vào body và submit
            document.body.appendChild(form);
            form.submit();
        } else {
            alert('Please input reason and upload a photo!');
        }
    };
    modalContent.appendChild(closeBtn);
    modalContent.appendChild(reasonInput);
    modalContent.appendChild(saveBtn);
    modal.appendChild(modalContent);
    document.body.appendChild(modal);
    modal.style.display = 'block';
    // Đóng modal khi nhấp ra ngoài modalContent
    window.onclick = function (event) {
        if (event.target === modal) {
            modal.style.display = 'none';
        }
    };
}

document.addEventListener('DOMContentLoaded', function () {
// Function to calculate time difference
    function timeAgo(time) {
        const now = new Date();
        const diff = Math.floor((now - new Date(time)) / 1000);
        if (diff < 60)
            return `${diff} seconds ago`;
        const mins = Math.floor(diff / 60);
        if (mins < 60)
            return `${mins} minutes ago`;
        const hours = Math.floor(mins / 60);
        if (hours < 24)
            return `${hours} hours ago`;
        const days = Math.floor(hours / 24);
        if (days < 7)
            return `${days} days ago`;
        const weeks = Math.floor(days / 7);
        if (weeks < 4)
            return `${weeks} weeks ago`;
        const months = Math.floor(days / 30);
        if (months < 12)
            return `${months} months ago`;
        const years = Math.floor(days / 365);
        return `${years} years ago`;
    }

// Function to update all notification times
    function updateTimes() {
        const timeElements = document.querySelectorAll('.notification-time');
        timeElements.forEach(function (element) {
            const time = element.getAttribute('data-time');
            element.textContent = timeAgo(time);
        });
    }

// Initial time update
    updateTimes();
    // Update times every minute
    setInterval(updateTimes, 60000);
});
// Đánh dấu tất cả thông báo là đã đọc
document.getElementById('markAllAsRead').addEventListener('click', function () {
    let notificationItems = document.querySelectorAll('.notification-item');
    notificationItems.forEach(function (item) {
        item.classList.add('read');
        let id = item.getAttribute('data-id');
        saveReadStatus(id);
    });
});
// Đánh dấu thông báo đơn lẻ là đã đọc
document.querySelectorAll('.notification-item').forEach(function (item) {
    item.addEventListener('click', function () {
        this.classList.add('read');
        let id = this.getAttribute('data-id');
        saveReadStatus(id);
    });
});
// JavaScript để sử dụng SweetAlert
document.addEventListener("DOMContentLoaded", function () {
    let err = document.getElementById("notice").textContent.trim();
    if (err) {
        showNotification(err); // Hiển thị thông báo
    }
});
function showNotification(message) {
    let icon = getNotificationIcon(message); // Lấy icon dựa trên nội dung của thông báo

    // Sử dụng SweetAlert để hiển thị thông báo với icon tương ứng
    Swal.fire({
        icon: icon, // Icon của thông báo (có thể là 'success', 'error', 'warning', 'info', 'question')
        title: 'Notice',
        text: message,
        confirmButtonColor: '#3085d6',
        confirmButtonText: 'OK'
    });
}

// Hàm để xác định icon dựa trên nội dung của thông báo
function getNotificationIcon(message) {
// Đặt các điều kiện hoặc logic dựa trên nội dung của message
// Ví dụ đơn giản:
    if (message.includes('someone') || message.includes('blank')) {
        return 'error';
    } else if (message.includes('success') || message.includes('complete') || message.includes('successfully')) {
        return 'success';
    } else if (message.includes('wait')) {
        return 'info';
    } else if (message.includes('other')) {
        return 'question';
    } else {
        return 'warning'; // Mặc định là info nếu không phù hợp với các điều kiện trên
    }
}
;
