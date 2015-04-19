(in-package :cl-user)
(defpackage demo
  (:use :cl :jss :cl-markup)
  (:export :render))

(in-package :demo)
(defun render (template model)
  (let ((model (jss:hashmap-to-hashtable 
		model 
		:keyfun #'read-from-string)))
    (funcall (eval (read-from-string template)) model)))