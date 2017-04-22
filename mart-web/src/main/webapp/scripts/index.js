
  function openScreen(screen_name) {
    $(".master_container").empty();
    if(screen_name=="Dashboard") {
      $(".master_container").load("dashboard.html");
    }else if(screen_name=="User") {
      $(".master_container").load("user.html");
    }else if(screen_name=="Owner") {
      $(".master_container").load("owner.html");
    }else if(screen_name=="Type") {
      $(".master_container").load("type.html");
    }else if(screen_name=="TypeItems") {
      $(".master_container").load("typeItems.html");
    }
  }


// configuration: http://codeseven.github.io/toastr/demo.html
toastr.options = {
  "closeButton": true,
  "debug": false,
  "newestOnTop": false,
  "progressBar": false,
  "positionClass": "toast-bottom-left",
  "preventDuplicates": false,
  "onclick": null,
  "showDuration": "300",
  "hideDuration": "1000",
  "timeOut": "3000",
  "extendedTimeOut": "1000",
  "showEasing": "swing",
  "hideEasing": "linear",
  "showMethod": "fadeIn",
  "hideMethod": "fadeOut"
}

function globalSuccessAlert(message) {
    toastr.success(message, "Success");
}
function globalErrorAlert(message) {
    toastr.error(message, "Error");
}
function globalWarningAlert(message) {
    toastr.warning(message, "Warning");
}

function logout(){
  
    $.ajax({
        type: "GET",
        url: 'rest/logout',
        success: function()
        { 
          reloadScreen();
        } 
        ,
        error: function(error)
        {
          reloadScreen();
        }
    });
  } 
    
  function reloadScreen(){
    var loc = window.location.href.split("?");
      if(loc.length>1){
        window.location.replace(loc[0]);
      }else{
        loc = window.location.href.split("#");
        if(loc.length>1){
          window.location.replace(loc[0]);
        }else{
          window.location.replace(window.location.href);
        }
      } 
  }


$(document).ready(function(){

   openScreen('Dashboard');


  $("#linkDashboard").click(function () {
      openScreen('Dashboard');
  });

  $("#linkSrchChrgSlip").click(function () {
      openScreen('Dashboard');
  });

  $("#linkSrchReceipt").click(function () {
      openScreen('Dashboard');
  });

  $("#linkUser").click(function () {
      openScreen('User');
  });

  $("#linkOwner").click(function () {
      openScreen('Owner');
  });

  $("#linkType").click(function () {
      openScreen('Type');
  });

  $("#linkTypeItems").click(function () {
      openScreen('TypeItems');
  });

  $("#linkLogout").click(function () {
      logout();
  });



});

