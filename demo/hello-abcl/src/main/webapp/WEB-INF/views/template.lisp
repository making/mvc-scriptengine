#'(lambda (model)
    (cl-markup:html5 
     (:title (gethash 'title model))
     (:body
      (:p (gethash 'body model)))))
