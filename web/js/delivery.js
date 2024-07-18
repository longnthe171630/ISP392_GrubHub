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
//const switchMode = document.getElementById('switch-mode');
//switchMode.addEventListener('change', function () {
//    if (this.checked) {
//        document.body.classList.add('dark');
//    } else {
//        document.body.classList.remove('dark');
//    }
//});

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


// Hàm mở modal
function openModal(status) {
    fetch('deliveryanalysis', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: 'status=' + encodeURIComponent(status)
    })
            .then(response => response.text())
            .then(html => {
                document.getElementById('modalContent').innerHTML = html;
                var modal = document.getElementById("myModal");
                modal.style.display = "block";
            })
            .catch(error => console.error('Error:', error));
}

// Hàm đóng modal
function closeModal() {
    const modal = document.getElementById("myModal");
    modal.style.display = "none";
}

//Map của dashboard
var map;
var currentMarker = null;
var currentInfowindow = null;
var currentLocation = {lat: 0, lng: 0}; // Khởi tạo tọa độ hiện tại
var userMarker = null; // Marker cho tọa độ bản thân


// Hàm tính khoảng cách Haversine
function haversineDistance(coord1, coord2) {
    const R = 6371; // Bán kính trái đất tính bằng km
    const lat1 = coord1.lat * (Math.PI / 180);
    const lat2 = coord2.lat * (Math.PI / 180);
    const deltaLat = (coord2.lat - coord1.lat) * (Math.PI / 180);
    const deltaLng = (coord2.lng - coord1.lng) * (Math.PI / 180);

    const a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
            Math.cos(lat1) * Math.cos(lat2) *
            Math.sin(deltaLng / 2) * Math.sin(deltaLng / 2);

    const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    return R * c; // Khoảng cách tính bằng km
}

function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 21.0346496, lng: 105.5149263},
        zoom: 8
    });

    // Lấy tọa độ hiện tại
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            currentLocation = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };
            // Đặt vị trí trung tâm bản đồ là vị trí hiện tại
            map.setCenter(currentLocation);
            placeUserMarker(); // Thêm marker cho tọa độ bản thân
            //placeMarker("Home"); // Thêm marker cho "Home"
        }, function () {
            alert("Không thể lấy vị trí của bạn.");
        });
    } else {
        alert("Trình duyệt của bạn không hỗ trợ geolocation.");
    }

    // Thêm sự kiện click vào bản đồ
    map.addListener('click', function (event) {
        // Có thể thêm hành động tại đây nếu cần
    });
}

function placeUserMarker() {
    // Tạo marker cho tọa độ bản thân
    userMarker = new google.maps.Marker({
        position: currentLocation,
        map: map,
        title: "Vị trí của bạn",
        icon: {
            url: "http://maps.google.com/mapfiles/ms/icons/blue-dot.png" // Biểu tượng marker
        }
    });
}

function searchAddress() {
    const address = document.getElementById('addressInput').value.toLowerCase();
    if (address) {
        const matchedAddresses = Object.keys(addressMapping).filter(key =>
            key.toLowerCase().includes(address) // Tìm kiếm không phân biệt hoa thường
        );
        if (matchedAddresses.length > 0) {
            // Hiển thị tất cả các địa chỉ tìm được
            matchedAddresses.forEach(key => {
                placeMarker(key);
            });
        } else {
            alert("Địa chỉ chưa cập nhật hoặc sai cú pháp!");
        }
    } else {
        alert("Vui lòng nhập địa chỉ!");
    }
}

function placeMarker(address) {
    // Nếu đã có marker hiện tại, xóa nó
    if (currentMarker) {
        currentMarker.setMap(null);
        if (currentInfowindow) {
            currentInfowindow.close();
        }
    }

    // Lấy tọa độ từ đối tượng ánh xạ
    var location = addressMapping[address];

    // Tạo marker mới
    currentMarker = new google.maps.Marker({
        position: location,
        map: map,
        title: address
    });

    // Tạo pop-up thông tin cho marker mới
    currentInfowindow = new google.maps.InfoWindow({
        content: '<div><strong>Địa chỉ:</strong> ' + address + '<br>' +
                '<ul>' +
                '<li><strong>Latitude:</strong> ' + location.lat + '</li>' +
                '<li><strong>Longitude:</strong> ' + location.lng + '</li>' +
                '</ul>' +
                '<a href="https://www.google.com/maps/search/?api=1&query=' + location.lat + ',' + location.lng + '" target="_blank">Xem trên Google Maps</a>' +
                '</div>'
    });

    // Tính khoảng cách từ vị trí hiện tại đến tọa độ marker
    const distance = haversineDistance(currentLocation, location);

    // Thêm khoảng cách vào nội dung pop-up
    currentInfowindow.setContent(currentInfowindow.getContent() +
            '<p><strong>Khoảng cách:</strong> ' + distance.toFixed(2) + ' km</p>');

    currentMarker.addListener('click', function () {
        currentInfowindow.open(map, currentMarker);
    });

    // Mở pop-up ngay lập tức khi thêm marker mới
    currentInfowindow.open(map, currentMarker);
}

// Gọi hàm khởi tạo bản đồ khi trang được tải
window.onload = initMap;

