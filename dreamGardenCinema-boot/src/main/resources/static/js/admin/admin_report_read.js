function deleteReview(button) {
  if (confirm("리뷰를 삭제하시겠습니까?")) {
      const row = button.closest("tr");
      const deleteFl = row.querySelector(".deleteFl");
      deleteFl.innerText = "Y";
      alert("리뷰가 삭제되었습니다.");
  }
}