document.addEventListener('DOMContentLoaded', function() {

    // Select the modal element
    const modal = document.querySelector('#myModal');
  
    // Select the close button element
    const closeButton = document.querySelector('#closeModal');
  
    // Select the button element that opens the modal
    const openButton = document.querySelector('#myButton');
  
    // Add a click event listener to the close button
    closeButton.addEventListener('click', function() {
      modal.style.display = 'none';
    });
  
    // Add a click event listener to the button that opens the modal
    openButton.addEventListener('click', function() {
      modal.style.display = 'block';
    });
  
    // Add a click event listener to the modal to close it when the user clicks outside the modal
    modal.addEventListener('click', function(event) {
      if (event.target === modal) {
        modal.style.display = 'none';
      }
    });
  
  });