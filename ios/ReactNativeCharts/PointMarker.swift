//
//  ChartMarkerImage.swift
//  Charts
//
//  Copyright 2015 Daniel Cohen Gindi & Philipp Jahoda
//  A port of MPAndroidChart for iOS
//  Licensed under Apache License 2.0
//
//  https://github.com/danielgindi/Charts
//

import Foundation
import Charts

open class PointMarker: MarkerImage
{
  @objc open var borderHeight: CGFloat
  @objc open var shadowBlur: CGFloat
  @objc open var shadowColor: CGColor
  @objc open var backgroundColor: CGColor
  @objc open var borderColor: CGColor
  
  public init(borderHeight: CGFloat, shadowBlur: CGFloat, shadowColor: CGColor, backgroundColor: CGColor, borderColor: CGColor)
  {
    self.borderHeight = borderHeight
    self.shadowBlur = shadowBlur
    self.shadowColor = shadowColor
    self.backgroundColor = backgroundColor
    self.borderColor = borderColor
  }
  
  open override func offsetForDrawing(atPoint point: CGPoint) -> CGPoint
  {
    let width = size.width
    let height = size.height
      
    return CGPoint(x: -(width/2), y: -(height/2))
  }
  
  open override func draw(context: CGContext, point: CGPoint)
  {
      let offset = offsetForDrawing(atPoint: point)
      
      let size = self.size
      
      context.saveGState()
    
      context.setShadow(offset: CGSize.zero, blur: shadowBlur, color: shadowColor)
    
      context.setFillColor(backgroundColor)
      context.setStrokeColor(borderColor)
      context.setLineWidth(borderHeight)
      
      let rect = CGRect(
          x: point.x + offset.x,
          y: point.y + offset.y,
          width: size.width,
          height: size.height)
    
      context.addEllipse(in: rect)
      context.drawPath(using: .fillStroke)
    
      context.restoreGState()
  }
}
