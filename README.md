## Bối cảnh
 Để làm việc với SQLite, ta phải thực hiện rất nhiều thao tác: xây dựng khung chương trình, mô hình, heplers, truy vấn, blah blah. Thực sự việc này tốn rất nhiều thời gian trong tiến trình phát triển phần mềm.
 Những người mới tiếp cận với SQLite sẽ phải bỏ ra nhiều giờ loay hoay trong khởi tạo, nhập xuất, xây dựng câu truy vấn và đặc biệt là code sẽ rất khó quản lý.
## Giải pháp trước kia
1. <strong>Viết thư viện hỗ trợ</strong>
 Hồi ở [Nikme](http://nikmesoft.com/), công ty viết ra một thư viện chung, hỗ trợ cho các thao tác lặp đi lặp lại này để giảm thiểu thời gian phát triển đồng thời giúp tương tác với SQLite tốt hơn, code dễ bảo trì hơn...
2. <strong>Sử dụng thư viện ORM (Object Relational Mapping)</strong>
 ORM thực sự hữu ích khi chuyển (ánh xạ) các bảng trong SQLite vào các đối tượng (model), mọi thao tác với SQLite bây giờ được thực hiện qua các đối tượng này. Tuy nhiên, việc sử dụng ORM phát sinh ra vấn đề về hiệu năng, độ ổn định. [ActiveAndroid](https://github.com/pardom/ActiveAndroid) là một ví dụ.
 ## Realm - Giải pháp thay thế cho SQLite
> Realm is a replacement for SQLite & Core Data. 
It can save you thousands of lines of code & weeks of work, 
and lets you craft amazing new user experiences.


 [Realm](https://realm.io/) là một cơ sở dữ liệu (CSDL) nguồn mở, sử dụng ORM, hoàn toàn miễn phí khi sử dụng, kể cả những sản phẩm thương mại.
Realm lưu dữ liệu trên bộ nhớ trong. Realm hỗ trợ đa nền tảng (hiện tại là Android, iOS, OSX), file CSDL có thể chia sẻ dễ dàng giữa các nền tảng trên.
 Realm luôn giữ tư tưởng nâng cao hiệu năng và giữ vững độ ổn định. Kết quả [benchmark](https://realm.io/news/realm-for-android/#realm-for-android) (có source code) cho thấy Realm nhanh hơn khoảng 2-10 lần trong các tác vụ đọc, ghi so với SQLite thuần và một số thư viện ORM phổ biến hiện nay.
 Việc làm quen, tiếp cận với Realm cực kỳ dễ dàng và đơn giản.
 Vậy Realm còn có gì để tự tin tuyên bố mình là giải pháp thay thế cho SQLite?
* Cộng đồng hỗ trợ đông đảo, nhiệt tình
Nếu có bất cứ vấn đề, thắc mắc gì, bạn sẽ được hỗ trợ trực tiếp bởi các lập trình viên phát triển Realm, và cộng đồng nguồn mở trên (github](https://github.com/realm) và [Stack Over Flow](https://stackoverflow.com/questions/tagged/realm?sort=newest).
* Dữ liệu được kết nối trực tiếp tới CSDL chứ không tạo ra bản sao khi truy vấn.
Do đó, các thao tác với dữ liệu sau khi truy vấn (tìm kiếm, sắp xếp, lọc,...) đều được thực hiện thẳng trên CSDL.
* Realm classes hỗ trợ nhiều trong việc tránh lỗi Null Pointer Exception.
Khi truy vấn không có kết quả, đối tượng `RealmResults` được trả về sẽ là rỗng, và phương thức `size()` sẽ cho ra 0.
* Hỗ trợ in-memory database
Trong số trường hợp, chúng ta chỉ cần tạo một CSDL tạm, cần tương tác (đọc/ghi) nhanh hơn nữa, chúng ta sẽ cần đến việc giữ CSDL trong bộ nhớ RAM (in-memory database). Realm hoàn toàn hỗ trợ giải pháp này.
* Hỗ trợ tạo model từ JSON hoặc từ InputStream (đọc từ file, load từ trên mạng về)
* Tương thích tốt với các thư viện thông dụng đang có (Gson, Retrofit, Otto, Robolectric...), hỗ trợ tích hợp tuỳ chỉnh
Bạn có thể định nghĩa cách các thư viện này tương tác với nhau, do đó bạn sẽ dùng các thư viện theo đúng ý mình muốn.
* Hỗ trợ custom migrating 
Việc cập nhật CSDL, thêm/bớt các bảng, trường đều được hỗ trợ một cách thuận tiện, đơn giản nhất thông qua `RealmMigration`
* Hỗ trợ mã hoá file CSDL
Hiện tại, Realm sử dụng AES-256 để mã hoá file CSDL.
* Tài liệu đầy đủ, chi tiết
Bạn có thể bắt tay tích hợp Realm vào dự án của bạn ngay chỉ sau nửa giờ đọc tài liệu hướng dẫn. Documentation cũng rất chi tiết, cụ thể và chuyên nghiệp.
Ngoài ra, Pealm cũng viết sẵn luôn các [ví dụ mẫu](https://github.com/realm/realm-java/tree/master/examples) trên github.