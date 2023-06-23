$(document).ready(function () {
    $('#summernote').summernote({
      placeholder: '내용을 작성하세요',
      height: 400,
      maxHeight: 400,
      callbacks: {
        onSubmit: function (contents) {
          // <p> 태그를 제거하고 줄 바꿈으로 처리
          var strippedHtml = contents.replace(/<p>/g, '').replace(/<\/p>/g, '<br>');
          $('#summernote').val(strippedHtml);
        }
      }
    });
  });
  

$(document).ready(function () {
    $('#summernote').summernote({
      placeholder: '내용을 작성하세요',
      height: 400,
      maxHeight: 400,
      disableEnterParagraphs: true
    });
  });
  
  