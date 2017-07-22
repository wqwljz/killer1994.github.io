   <script>
		var fontsize = 3;
			
		Array.prototype.forEach = function(callback){
            for(var i=0;i<this.length;i++){
                callback.call(this[i]);
            }
        }
		
		var Dot = function(centerX , centerY , centerZ , radius){
            this.dx = centerX;		// 原来的位置
            this.dy = centerY;
            this.dz = centerZ;
            this.tx = 0;			// 保存粒子聚合后又飞散开的位置
            this.ty = 0;
            this.tz = 0;
            this.z = centerZ;
            this.x = centerX;
            this.y = centerY;
            this.radius = radius;
        }

        Dot.prototype = {
			// 在canvas 上画一个点，方法是画一个圆弧（arc），填充成一个点（fill）
            paint:function(){
                context.save();
                context.beginPath();
                var scale = focallength/(focallength + this.z);
                context.arc(canvas.width/2 + (this.x-canvas.width/2)*scale , canvas.height/2 + (this.y-canvas.height/2) * scale, this.radius*scale , 0 , 2*Math.PI);
                context.fillStyle = "rgba(50,50,50,"+ scale +")";
                context.fill()
                context.restore();
            }
        }
		
		function drawText(text){
            context.save();
            context.font = "200px 微软雅黑 bold";
            context.fillStyle = "rgba(168,168,168,1)";
            context.textAlign = "center";
            context.textBaseline = "middle";
            context.fillText(text , canvas.width/2 , canvas.height/2);
            context.restore();
        }
		
		function getimgData(text){
            drawText(text);
			
            var imgData = context.getImageData(0,0,canvas.width , canvas.height);
            context.clearRect(0,0,canvas.width , canvas.height);
            var dots = [];
			// 文字像素化
            for(var x=0;x<imgData.width;x+= 2*fontsize){
                for(var y=0;y<imgData.height;y+= 2*fontsize){
                    var i = (y*imgData.width + x)*4;
                    if(imgData.data[i] >= 128){
                        var dot = new Dot(x-fontsize , y-fontsize , 0 , fontsize);
                        dots.push(dot);
                    }
                }
            }
            return dots;
			
        }

        window.onload = function(){
            canvas = document.getElementById("cas");
            context = canvas.getContext('2d');
            focallength = 250;

            var dots = getimgData(document.getElementById('name').value);;
            var pause = false;
            initAnimate();
            function initAnimate(){
                dots.forEach(function(){
                    this.x = Math.random()*canvas.width;
                    this.y = Math.random()*canvas.height;
                    this.z = Math.random()*focallength*2 - focallength;

                    this.tx = Math.random()*canvas.width;
                    this.ty = Math.random()*canvas.height;
                    this.tz = Math.random()*focallength*2 - focallength;
                    this.paint();
                });
				
                animate();
            }

            //计算帧速率
            var lastTime;
            var derection = true;
            function animate(){
                animateRunning = true;
                var thisTime = +new Date();
                context.clearRect(0,0,canvas.width , canvas.height);
                dots.forEach(function(){
                    var dot = this;
                    if(derection){
                        if (Math.abs(dot.dx - dot.x) < 0.1 && Math.abs(dot.dy - dot.y) < 0.1 && Math.abs(dot.dz - dot.z)<0.1) {
                            dot.x = dot.dx;
                            dot.y = dot.dy;
                            dot.z = dot.dz;
                            if(thisTime - lastTime > 300) derection = false;
                        } else {
                            dot.x = dot.x + (dot.dx - dot.x) * 0.1;
                            dot.y = dot.y + (dot.dy - dot.y) * 0.1;
                            dot.z = dot.z + (dot.dz - dot.z) * 0.1;
                            lastTime = +new Date()
                        }
                    }
                    else {
                        if (Math.abs(dot.tx - dot.x) < 0.1 && Math.abs(dot.ty - dot.y) < 0.1 && Math.abs(dot.tz - dot.z)<0.1) {
                            dot.x = dot.tx;
                            dot.y = dot.ty;
                            dot.z = dot.tz;
                            pause = true;
                        } else {
                            dot.x = dot.x + (dot.tx - dot.x) * 0.1;
                            dot.y = dot.y + (dot.ty - dot.y) * 0.1;
                            dot.z = dot.z + (dot.tz - dot.z) * 0.1;
                            pause = false;
                        }
                    }
                    dot.paint();
                });
                if(!pause) {
                    if("requestAnimationFrame" in window){
                        requestAnimationFrame(animate);
                    }
                    else if("webkitRequestAnimationFrame" in window){
                        webkitRequestAnimationFrame(animate);
                    }
                    else if("msRequestAnimationFrame" in window){
                        msRequestAnimationFrame(animate);
                    }
                    else if("mozRequestAnimationFrame" in window){
                        mozRequestAnimationFrame(animate);
                    }
                }
            }

            document.getElementById('startBtn').onclick = function(){
                if(!pause) return;
                dots = getimgData(document.getElementById('name').value);
                derection=true;
                pause = false;
                initAnimate();
				//animate();
            }
        }

        

    </script>
