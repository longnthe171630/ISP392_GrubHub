const allSideMenu = document.querySelectorAll('#sidebar .side-menu.top li a');

allSideMenu.forEach(item => {
    const li = item.parentElement;

    item.addEventListener('click', function () {
        allSideMenu.forEach(i => {
            i.parentElement.classList.remove('active');
        })
        li.classList.add('active');
    })
});




// TOGGLE SIDEBAR
const menuBar = document.querySelector('#content nav .bx.bx-menu');
const sidebar = document.getElementById('sidebar');

menuBar.addEventListener('click', function () {
    sidebar.classList.toggle('hide');
})







const searchButton = document.querySelector('#content nav form .form-input button');
const searchButtonIcon = document.querySelector('#content nav form .form-input button .bx');
const searchForm = document.querySelector('#content nav form');

searchButton.addEventListener('click', function (e) {
    if (window.innerWidth < 576) {
        e.preventDefault();
        searchForm.classList.toggle('show');
        if (searchForm.classList.contains('show')) {
            searchButtonIcon.classList.replace('bx-search', 'bx-x');
        } else {
            searchButtonIcon.classList.replace('bx-x', 'bx-search');
        }
    }
})





if (window.innerWidth < 768) {
    sidebar.classList.add('hide');
} else if (window.innerWidth > 576) {
    searchButtonIcon.classList.replace('bx-x', 'bx-search');
    searchForm.classList.remove('show');
}


window.addEventListener('resize', function () {
    if (this.innerWidth > 576) {
        searchButtonIcon.classList.replace('bx-x', 'bx-search');
        searchForm.classList.remove('show');
    }
})



const switchMode = document.getElementById('switch-mode');

switchMode.addEventListener('change', function () {
    if (this.checked) {
        document.body.classList.add('dark');
    } else {
        document.body.classList.remove('dark');
    }
})

document.addEventListener("DOMContentLoaded", function () {
    // Lấy tất cả các liên kết trong menu bên
    const sideMenuLinks = document.querySelectorAll(".side-menu a");

    sideMenuLinks.forEach(link => {
        // Kiểm tra nếu liên kết trỏ đến phần tử trên cùng một trang
        link.addEventListener("click", function (event) {
            const targetId = this.getAttribute("href").substring(1); // Lấy ID của phần tử đích
            const targetElement = document.getElementById(targetId);

            if (targetElement) {
                event.preventDefault(); // Ngăn chặn hành động mặc định của liên kết
                targetElement.scrollIntoView({behavior: "smooth"}); // Trượt đến phần tử đích
            }
        });
    });
});
//Mở modal                
// Open modal function// Function to open the modal and load productList.jsp content
function openModal(id) {
    // Gửi yêu cầu đến Servlet với ID sản phẩm
    fetch('admin', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: 'id=' + id
    })
            .then(response => response.text())
            .then(html => {
                // Đặt nội dung được trả về vào modal và hiển thị modal
                document.getElementById('modalContent').innerHTML = html;
                var modal = document.getElementById("myModal");
                modal.style.display = "block";
            })
            .catch(error => console.error('Error:', error));
}



// Đóng modal khi nhấp chuột vào bên ngoài modal
window.onclick = function (event) {
    var modal = document.getElementById('myModal');
    if (event.target == modal) {
        closeModal();
    }
}
function openModalCus(id) {
    // Gửi yêu cầu đến Servlet với ID sản phẩm
    fetch('cus', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: 'id=' + id
    })
            .then(response => response.text())
            .then(html => {
                // Đặt nội dung được trả về vào modal và hiển thị modal
                document.getElementById('modalContent').innerHTML = html;
                document.getElementById('myModal').style.display = "block";
            })
            .catch(error => console.error('Error:', error));
}

// Đóng modal khi nhấp vào dấu X
function closeModal() {
    document.getElementById('myModal').style.display = "none";
}

 


