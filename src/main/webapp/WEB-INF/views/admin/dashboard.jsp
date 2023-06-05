<!-- statistics.jsp -->

<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
  <title>Thống kê</title>
</head>

<body>
  <div class="container">
    <h1>Thống kê</h1>

   <form action="statistics" method="post" enctype="multipart/form-data">
      <div class="form-group">
        <label for="year">Chọn năm:</label>
        <select id="year" name="year" class="form-control">
          <option value="2022">2022</option>
          <option value="2023">2023</option>
          <option value="2024">2024</option>
          <!-- Add other years if needed -->
        </select>
      </div>

      <button type="submit" class="btn btn-primary">Thống kê</button>
    </form>

   
  </div>

  
</body>

</html>
