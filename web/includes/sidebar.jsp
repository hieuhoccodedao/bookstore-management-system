<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h3>Lọc theo giá</h3>
<form action="Home" method="GET">
    <div class="filter-group">
        <input type="radio" id="all" name="priceOption" value="all" 
               ${selectedPrice == 'all' || empty selectedPrice ? 'checked' : ''}>
        <label for="all">Tất cả</label>
    </div>
    <div class="filter-group">
        <input type="radio" id="under100" name="priceOption" value="under100" 
               ${selectedPrice == 'under100' ? 'checked' : ''}>
        <label for="under100">Dưới 100.000đ</label>
    </div>
    <div class="filter-group">
        <input type="radio" id="100to300" name="priceOption" value="100to300" 
               ${selectedPrice == '100to300' ? 'checked' : ''}>
        <label for="100to300">100.000đ - 300.000đ</label>
    </div>
    <div class="filter-group">
        <input type="radio" id="above300" name="priceOption" value="above300" 
               ${selectedPrice == 'above300' ? 'checked' : ''}>
        <label for="above300">Trên 300.000đ</label>
    </div>
    <button type="submit" class="btn-apply">Áp dụng</button>
</form>