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


// Hàm xử lý toggle dropdown
function toggleDropdown(dropdownId) {
    const dropdown = document.getElementById(dropdownId);
    dropdown.classList.toggle('show');

    // Thêm sự kiện click ra ngoài window để đóng dropdown
    document.addEventListener('click', closeDropdownOutside);
}

// Sự kiện click ra ngoài để đóng dropdown
function closeDropdownOutside(event) {
    const dropdownContainers = document.querySelectorAll('.dropdown-container');

    dropdownContainers.forEach(container => {
        const dropdownContent = container.querySelector('.dropdown-content') ||
                container.querySelector('.dropdown-content-1') ||
                container.querySelector('.dropdown-content-2');

        // Kiểm tra click có nằm trong container của dropdown hay không
        const isClickedInsideContainer = container.contains(event.target);

        // Nếu không phải click vào toggleElement của dropdown đó và không nằm trong container, đóng dropdown
        if (!isClickedInsideContainer) {
            dropdownContent.classList.remove('show');
        }
    });

    // Gỡ bỏ sự kiện click ra ngoài sau khi đã xử lý
    document.removeEventListener('click', closeDropdownOutside);
}

//On/Off mode dark/night
const switchMode = document.getElementById('switch-mode');

switchMode.addEventListener('change', function () {
    if (this.checked) {
        document.body.classList.add('dark');
    } else {
        document.body.classList.remove('dark');
    }
})

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
    if (event.target == modal) {
        modal.style.display = "none";
    }
}


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

    var reasonInput = document.createElement('textarea');
    reasonInput.setAttribute('placeholder', 'What happend is it?...');
    reasonInput.style.width = '400px';   // Đặt chiều rộng textarea
    reasonInput.style.height = '125px';  // Đặt chiều cao textarea
    // Thiết lập kiểu chữ và kích thước
    reasonInput.style.fontFamily = 'Arial, sans-serif'; // Font chữ và fallback
    reasonInput.style.fontSize = '18px';  // Kích thước chữ
    reasonInput.style.padding = '5px';  // Thiết lập padding là 5px

    var saveBtn = document.createElement('button');
    saveBtn.innerText = 'Xác nhận';
    saveBtn.onclick = function () {
        var reason = reasonInput.value.trim();
        if (reason !== '') {
            // Tạo form động
            var form = document.createElement('form');
            form.method = 'POST';
            form.action = 'deliverynotice';

            // Tạo input ẩn cho lý do
            var reasonField = document.createElement('input');
            reasonField.type = 'hidden';
            reasonField.name = 'reason';
            reasonField.value = reason;
            form.appendChild(reasonField);

            // Tạo input ẩn cho order_id
            var orderField = document.createElement('input');
            orderField.type = 'hidden';
            orderField.name = 'id';
            orderField.value = id;
            form.appendChild(orderField);

            // Append form vào body và submit
            document.body.appendChild(form);
            form.submit();
        } else {
            alert('Please input reason failure!.');
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
