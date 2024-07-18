/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
// Đối tượng ánh xạ địa chỉ và tọa độ
var addressMapping = {
    "1 Phố Hàng Bạc, Phường Hàng Bạc, Quận Hoàn Kiếm, Hà Nội": {lat: 21.0285,lng: 105.8542},
    "48 Lý Thường Kiệt, Phường Trần Hưng Đạo, Quận Hoàn Kiếm, Hà Nội": {lat: 21.0278, lng: 105.8562},
    "10 Đinh Tiên Hoàng, Phường Hàng Trống, Quận Hoàn Kiếm, Hà Nội": {lat: 21.0288, lng: 105.8524},
    "123 Trần Hưng Đạo, Phường Cửa Nam, Quận Hoàn Kiếm, Hà Nội": {lat: 21.0311, lng: 105.8535},
    "24 Lê Thái Tổ, Phường Hàng Trống, Quận Hoàn Kiếm, Hà Nội": {lat: 21.0320, lng: 105.8538},
    "99 Nguyễn Du, Phường Nguyễn Du, Quận Hai Bà Trưng, Hà Nội": {lat: 20.9953, lng: 105.8552},
    "79 Lê Văn Hưu, Phường Lê Đại Hành, Quận Hai Bà Trưng, Hà Nội": {lat: 20.9988, lng: 105.8550},
    "30 Tôn Đức Thắng, Phường Quốc Tử Giám, Quận Đống Đa, Hà Nội": {lat: 21.0225, lng: 105.8323},
    "55 Nguyễn Thái Học, Phường Quốc Tử Giám, Quận Đống Đa, Hà Nội": {lat: 21.0200, lng: 105.8321},
    "20 Nguyễn Huy Tưởng, Phường Thanh Xuân Trung, Quận Thanh Xuân, Hà Nội": {lat: 20.9938, lng: 105.8105},
    "40 Nguyên Hồng, Phường Nguyên Hồng, Quận Đống Đa, Hà Nội": {lat: 21.0215, lng: 105.8346},
    "15 Trần Bình, Phường Trần Hữu Dực, Quận Nam Từ Liêm, Hà Nội": {lat: 20.9956, lng: 105.7495},
    "100 Hoàng Quốc Việt, Phường Nghĩa Đô, Quận Cầu Giấy, Hà Nội": {lat: 21.0428, lng: 105.7874},
    "8 Phố Nguyễn Khánh Toàn, Phường Nghĩa Tân, Quận Cầu Giấy, Hà Nội": {lat: 21.0445, lng: 105.7851},
    "88 Đường Láng, Phường Láng Thượng, Quận Đống Đa, Hà Nội": {lat: 21.0206, lng: 105.8176},
    "121 Trần Duy Hưng, Phường Trung Hòa, Quận Cầu Giấy, Hà Nội": {lat: 20.9952, lng: 105.7891},
    "256 Đường Nguyễn Trãi, Phường Thanh Xuân Trung, Quận Thanh Xuân, Hà Nội": {lat: 20.9926, lng: 105.7982},
    "23 Lê Văn Lương, Phường Nhân Chính, Quận Thanh Xuân, Hà Nội": {lat: 20.9931, lng: 105.7864},
    "5 Phố Hàng Bè, Phường Hàng Bạc, Quận Hoàn Kiếm, Hà Nội": {lat: 21.0284, lng: 105.8555},
    "9 Phố Hàng Mã, Phường Hàng Gai, Quận Hoàn Kiếm, Hà Nội": {lat: 21.0292, lng: 105.8536},
    "45 Đường Tôn Đức Thắng, Phường Quốc Tử Giám, Quận Đống Đa, Hà Nội": {lat: 21.0221, lng: 105.8326},
    "22 Phố Hàng Đào, Phường Hàng Đào, Quận Hoàn Kiếm, Hà Nội": {lat: 21.0310, lng: 105.8530},
    "65 Hoàng Quốc Việt, Cầu Giấy, Hà Nội": {lat: 21.0428, lng: 105.7874},
    "12 Đặng Văn Ngữ, Đống Đa, Hà Nội": {lat: 21.0245, lng: 105.8263},
    "5 Hoàng Quốc Việt, Cầu Giấy, Hà Nội": {lat: 21.0415, lng: 105.7897},
    "119 Hàng Bông, Quận Hoàn Kiếm, Hà Nội": {lat: 21.0289, lng: 105.8533},
    "116 Thanh Niên, Tây Hồ, Hà Nội": {lat: 21.0806, lng: 105.8350},
    "12 Lê Duẩn, Hai Bà Trưng, Hà Nội": {lat: 20.9953, lng: 105.8528},
    "191 Bà Triệu, Hai Bà Trưng, Hà Nội": {lat: 20.9992, lng: 105.8517},
    "91 Cầu Diễn, Bắc Từ Liêm, Hà Nội": {lat: 21.0600, lng: 105.7351},
    "48 Lý Thường Kiệt, Trần Hưng Đạo, Hoàn Kiếm, Hà Nội": {lat: 21.0278, lng: 105.8562},
    "49 Thái Thịnh, Thịnh Quang, Đống Đa, Hà Nội": {lat: 21.0214, lng: 105.8308}
};

